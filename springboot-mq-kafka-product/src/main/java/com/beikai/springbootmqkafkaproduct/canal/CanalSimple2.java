package com.beikai.springbootmqkafkaproduct.canal;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry.*;
import com.alibaba.otter.canal.protocol.Message;
import com.beikai.springbootmqkafkaproduct.unit.RedisUnits;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2019/10/24
 * Time: 4:34 下午
 * Description: No Description
 */
@Component
@Slf4j
public class CanalSimple2 {

    static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    static final String SEP = SystemUtils.LINE_SEPARATOR;
    static String context_format = null;
    static String row_format = null;
    static String transaction_format = null;

    @Autowired
    private RedisUnits redisUnits;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    static {
        context_format = SEP + "****************************************************" + SEP;
        context_format += "* Batch Id: [{}] ,count : [{}] , memsize : [{}] , Time : {}" + SEP;
        context_format += "* Start : [{}] " + SEP;
        context_format += "* End : [{}] " + SEP;
        context_format += "****************************************************" + SEP;

        row_format = SEP
                + "----------------> binlog[{}:{}] , name[{},{}] , eventType : {} , executeTime : {}({}) , gtid : ({}) , delay : {} ms"
                + SEP;

        transaction_format = SEP
                + "================> binlog[{}:{}] , executeTime : {}({}) , gtid : ({}) , delay : {}ms"
                + SEP;

    }

    @PostConstruct
    public void startCanal() {

        //创建 destination
        String destination = "example";
        // 创建ip
        String ip = "172.16.195.133";
        // 创建用户
        String username = "canal";
        // 创建密码
        String password = "canal";
        // 创建端口
        Integer port = 11111;
        // 创建连接
        CanalConnector canalConnector = CanalConnectors.newSingleConnector(new InetSocketAddress(ip, port),
                destination,
                username,
                password
        );

        start(destination, canalConnector);


    }

    /**
     * 创建开始方法
     *
     * @param destination
     * @param canalConnector
     */
    private void start(String destination, CanalConnector canalConnector) {

        Assert.notNull(canalConnector, "canalConnector 是空的。。。");

        process(destination, canalConnector);
    }


    private void process(String destination, CanalConnector canalConnector) {
        int batchSize = 5 * 1024;

        while (true) {
            try {
                MDC.put("destination", destination);
                // 连接canal
                log.info("\n----------------------------------> 开始连接 <----------------------------------");
                canalConnector.connect();
                log.info("\n----------------------------------> 连接成功 <----------------------------------");
                // 订阅
                log.info("\n----------------------------------> 开始订阅 <----------------------------------");
                canalConnector.subscribe();
                log.info("\n----------------------------------> 订阅成功 <----------------------------------");
                while (true) {
                    // 获取指定数量的数据
                    Message message = canalConnector.getWithoutAck(batchSize);
                    long batchId = message.getId();
                    int size = message.getEntries().size();
                    if (batchId == -1 || size == 0) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {

                        }
                        log.error("---------------------------------->  batchId == -1 || size == 0  <----------------------------------");
                    } else {
                        // 打印信息
                        printSummary(message, batchId, size);
                        printEntry(message.getEntries());
                    }

                    if (batchId != -1) {
                        log.info("=============================> 提交确认 <=============================");
                        canalConnector.ack(batchId);
                        log.info("=============================> 确认成功 <=============================");
                    }
                }
            } catch (IllegalArgumentException e) {
                log.error("\n----------------------------------> 执行process方法出错 <----------------------------------");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {

                }
                log.error("\n " + e);
            } finally {
                log.info("\n----------------------------------> 关闭连接 <----------------------------------");
                canalConnector.disconnect();
                log.info("\n----------------------------------> 关闭成功 <----------------------------------");
            }

        }
    }

    private void printEntry(List<Entry> entries) {
        for (Entry entry : entries) {
            // 获取执行时间
            long executeTime = entry.getHeader().getExecuteTime();
            // 获取当前时间
            long delayTime = System.currentTimeMillis() - executeTime;
            Date date = new Date(executeTime);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);

            EntryType entryType = entry.getEntryType();

            if (entryType == EntryType.TRANSACTIONBEGIN || entryType == EntryType.TRANSACTIONEND) {
                if (entryType == EntryType.TRANSACTIONBEGIN) {
                    TransactionBegin begin = null;
                    try {
                        begin = TransactionBegin.parseFrom(entry.getStoreValue());
                    } catch (InvalidProtocolBufferException e) {
                        throw new RuntimeException("parse event has an error , data:" + entry.toString(), e);
                    }
                    // 打印事务头信息，执行的线程id，事务耗时
                    log.info(transaction_format,
                            new Object[] { entry.getHeader().getLogfileName(),
                                    String.valueOf(entry.getHeader().getLogfileOffset()),
                                    String.valueOf(entry.getHeader().getExecuteTime()), simpleDateFormat.format(date)

                                    //, entry.getHeader().getGtid(), String.valueOf(delayTime)

                    });
                    log.info(" BEGIN ----> Thread id: {}", begin.getThreadId());
                    // 打印 xa 信息
                    printXAInfo(begin.getPropsList());
                }else if (entryType == EntryType.TRANSACTIONEND){
                    TransactionEnd end = null;
                    try {
                        end = TransactionEnd.parseFrom(entry.getStoreValue());
                    } catch (InvalidProtocolBufferException e) {
                        throw new RuntimeException("parse event has an error , data:" + entry.toString(), e);
                    }
                    // 打印事务提交信息，事务id
                    log.info("----------------\n");
                    log.info(" END ----> transaction id: {}", end.getTransactionId());
                    printXAInfo(end.getPropsList());
                    log.info(transaction_format,
                            new Object[] { entry.getHeader().getLogfileName(),
                                    String.valueOf(entry.getHeader().getLogfileOffset()),
                                    String.valueOf(entry.getHeader().getExecuteTime()), simpleDateFormat.format(date)
                                    //, entry.getHeader().getGtid(), String.valueOf(delayTime)
                    });
                }
                // 不是这两种类型的跳过
                continue;
            }

            // 打印 rowdata 信息
            if (entryType == EntryType.ROWDATA) {
                log.info("\n----------------------------------> 开始处理rowdata 信息 <----------------------------------");
                RowChange rowChange = null;

                try {
                    // 反序列化数据
                    rowChange = RowChange.parseFrom(entry.getStoreValue());
                } catch (InvalidProtocolBufferException e) {
                    throw new RuntimeException("parse event has an error , data:" + entry.toString(), e);
                }

                EventType eventType = rowChange.getEventType();

                log.info(row_format,
                        new Object[] { entry.getHeader().getLogfileName(),
                                String.valueOf(entry.getHeader().getLogfileOffset()), entry.getHeader().getSchemaName(),
                                entry.getHeader().getTableName(), eventType,
                                String.valueOf(entry.getHeader().getExecuteTime()), simpleDateFormat.format(date)
                                //, entry.getHeader().getGtid(), String.valueOf(delayTime)

                });

                if (eventType == EventType.QUERY || rowChange.getIsDdl()) {
                    log.info("用到的sql 语句是 ： " + rowChange.getSql() + "  sep : " + SEP);
                    continue;
                }

                log.info("用到的sql 语句是 ： " + rowChange.getSql() + "  sep : " + SEP);

                printXAInfo(rowChange.getPropsList());
                log.info("\n----------------------------------> 打印列信息 <----------------------------------");
                for (RowData rowData : rowChange.getRowDatasList()) {
                    if (eventType == EventType.DELETE) {
                        printColumn(rowData.getBeforeColumnsList());

                    }else if (eventType == EventType.INSERT){
                        printColumn(rowData.getAfterColumnsList());
                    }else {
                        printColumn(rowData.getAfterColumnsList());
                    }
                }

            }
        }

    }

    /**
     * 打印列信息
     * @param columns
     */
    private void printColumn(List<Column> columns) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Column column : columns) {

            try {
                if (StringUtils.containsIgnoreCase(column.getMysqlType(),"BLOB") ||
                StringUtils.containsIgnoreCase(column.getMysqlType(),"BINARY")) {

                    stringBuilder.append(column.getName() + " : " +
                            new String(column.getValue().getBytes("ISO-8859-1"),"UTF-8"));
                }else {
                    stringBuilder.append(column.getName() +" : " + column.getValue());
                }
            } catch (UnsupportedEncodingException e) {
            }

            stringBuilder.append("   type = " + column.getMysqlType());

            if (column.getUpdated()) {
                stringBuilder.append("   upate = " + column.getUpdated());
            }

            stringBuilder.append(SEP);

        }

        //redisUnits.set("canal-"+columns.get(0).getValue(),stringBuilder.toString());
    }

    private void printXAInfo(List<Pair> pairs) {

        if (pairs == null) {
            return;
        }

        String xaType = null;
        String xaXid = null;
        for (Pair pair : pairs) {
            String key = pair.getKey();
            if (StringUtils.endsWithIgnoreCase(key, "XA_TYPE")) {
                xaType = pair.getValue();
            } else if (StringUtils.endsWithIgnoreCase(key, "XA_XID")) {
                xaXid = pair.getValue();
            }
        }

        if (xaType != null && xaXid != null) {
            log.info(" ------> " + xaType + " " + xaXid);
        }
    }

    /**
     * 打印摘要
     *
     * @param message
     * @param batchId
     * @param size
     */
    private void printSummary(Message message, long batchId, int size) {

        log.info("\n----------------------------------> 开始打印摘要 <----------------------------------");

        long memsize = 0;
        List<Entry> entries = message.getEntries();
        for (Entry entry : entries) {
            memsize += entry.getHeader().getEventLength();
        }

        // 获取起止点
        String startPosition = null;
        String endPosition = null;

        if (!CollectionUtils.isEmpty(entries)) {
            startPosition = buildPositionForDump(entries.get(0));
            endPosition = buildPositionForDump(entries.get(entries.size() - 1));
        }

        // 打印 日志相关信息
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        log.info(context_format, new Object[]{batchId, size, memsize, format.format(new Date()), startPosition,
                endPosition});

        log.info("\n----------------------------------> 打印摘要结束 <----------------------------------");

    }

    /**
     * 创建起止点
     *
     * @param entry
     * @return
     */
    private String buildPositionForDump(Entry entry) {

        Header header = entry.getHeader();

        long time = header.getExecuteTime();
        Date date = new Date(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);

        String position = header.getLogfileName() + ":" + header.getLogfileOffset() + ":" + header.getExecuteTime() + "(" + simpleDateFormat.format(date) + ")";

        /*if (StringUtils.isNotEmpty(entry.getHeader().getGtid())) {
            position += " gtid(" + entry.getHeader().getGtid() + ")";
        }*/

        return position;
    }


    //下面是往redis里操作数据
    private void redisInsert( List<Column> columns){
        JSONObject json=new JSONObject();
        for (Column column : columns) {
            json.put(column.getName(), column.getValue());
        }
        if(columns.size()>0){
            log.info("redisInsert"+" key -> " + columns.get(0).getValue() +" value -> " + json.toJSONString());
            redisUnits.set("canal"+columns.get(0).getValue(),json.toJSONString());

            log.info(" \n ------------------------------- \n value : "
                    + redisUnits.get("canal"+columns.get(0).getValue())
                    + "\n key : "
                    + "canal"+columns.get(0).getValue() +" \n -------------------------------"
            );

        }
    }

    private  void redisUpdate( List<Column> columns){
        JSONObject json=new JSONObject();
        for (Column column : columns) {
            json.put(column.getName(), column.getValue());
//            logger.info("更新到redis后的字段为："+column.getName()+"  值为："+column.getValue());
        }
        if(columns.size()>0){
//            RedisUtil.stringSet("ceshi:"+ columns.get(0).getValue(),json.toJSONString());//加上“ceshi：”的话，是redis的结构有“ceshi”的文件夹，数据会在这个文件夹下
            log.info("redisUpdate "+"key -> " + columns.get(0).getValue() +" value -> " + json.toJSONString());
            redisUnits.set("canal"+columns.get(0).getValue(),json.toJSONString());

            log.info("\n -------------------------------> \n value : "
                    + redisUnits.get("canal"+columns.get(0).getValue())
                    + "\n key : "
                    + "canal"+columns.get(0).getValue()
                    +" \n -------------------------------"
            );
        }
    }

    private void redisDelete( List<Column> columns){
        JSONObject json=new JSONObject();
        for (Column column : columns) {
            json.put(column.getName(), column.getValue());
        }
        if(columns.size()>0){
//            RedisUtil.delKey("ceshi:"+ columns.get(0).getValue());
            log.info("redisDelete key -> " + "canal"+columns.get(0).getValue());
            redisUnits.del("canal"+columns.get(0).getValue());
        }
    }


}
