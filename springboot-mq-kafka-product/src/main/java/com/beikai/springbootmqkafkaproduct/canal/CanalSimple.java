package com.beikai.springbootmqkafkaproduct.canal;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry.*;
import com.alibaba.otter.canal.protocol.Message;
import com.beikai.springbootmqkafkaproduct.unit.RedisUnits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2019/10/17
 * Time: 5:54 下午
 * Description: No Description
 */
@Component
public class CanalSimple {
    //日志
    private  final Logger logger = LoggerFactory.getLogger(CanalSimple.class);

    @Autowired
    private RedisUnits redisUnits;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @PostConstruct
    public  void syn() {
        // 创建链接
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress("172.16.195.133",
                11111), "example", "", "");//IP和端口为上文中 canal.properties中的IP和端口，“example”为默认，用户名密码不填

        logger.info("正在连接...");
        System.out.println("正在连接...");
        System.out.println(connector);
        int batchSize = 1000;
        try {
            connector.connect();
            connector.subscribe(".*\\..*");
            logger.info("连接成功");
            System.out.println("连接成功");
            connector.rollback();
            while (true) {
                Message message = connector.getWithoutAck(batchSize); // 获取指定数量的数据
                long batchId = message.getId();
                int size = message.getEntries().size();
                if (batchId == -1 || size == 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    printEntry(message.getEntries());
                }

                connector.ack(batchId); // 提交确认
                // connector.rollback(batchId); // 处理失败, 回滚数据
            }

        } finally {
            connector.disconnect();
            logger.info("连接释放成功");
            System.out.println("连接释放成功");
        }
    }

    private  void printEntry( List<Entry> entrys) {
        for (Entry entry : entrys) {
            if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {
                continue;
            }

            RowChange rowChage = null;
            try {
                rowChage = RowChange.parseFrom(entry.getStoreValue());
            } catch (Exception e) {
                throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),
                        e);
            }

            EventType eventType = rowChage.getEventType();
            System.out.println(String.format("================> binlog[%s:%s] , name[%s,%s] , eventType : %s",
                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
                    eventType));

            for (RowData rowData : rowChage.getRowDatasList()) {
                if (eventType == EventType.DELETE) {
                    redisDelete(rowData.getBeforeColumnsList());
                    logger.info("删除redis数据成功");
                } else if (eventType == EventType.INSERT) {
                    redisInsert(rowData.getAfterColumnsList());
                    logger.info("成功新增数据到redis");
                } else {
                    System.out.println("------->修改之前的数据为：");
//                    printColumn(rowData.getBeforeColumnsList());
                    logger.info("修改之前的数据为："+printColumn(rowData.getBeforeColumnsList()));
                    System.out.println("------->修改之后的数据为：");
                    redisUpdate(rowData.getAfterColumnsList());
//                    printColumn(rowData.getBeforeColumnsList());
                    logger.info("修改之后的数据为："+printColumn(rowData.getAfterColumnsList()));
                }
            }
        }
    }



    private  String printColumn( List<Column> columns) {
        String s = null;
        for (Column column : columns) {
            s = column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated();
            System.out.println(s);
            logger.info("字段名为："+column.getName() + " 值为: " + column.getValue() + "  是否更新为：" +Integer.toString(column.getUpdated()==true?1:0));
            s=s+"    ";
        }
        return s;
    }

    //下面是往redis里操作数据
    private void redisInsert( List<Column> columns){
        JSONObject json=new JSONObject();
        for (Column column : columns) {
            json.put(column.getName(), column.getValue());
        }
        if(columns.size()>0){
            logger.info("redisInsert"+" key -> " + columns.get(0).getValue() +" value -> " + json.toJSONString());
            redisUnits.set("canal"+columns.get(0).getValue(),json.toJSONString());

            logger.info(" \n ------------------------------- \n value : "
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
            logger.info("redisUpdate "+"key -> " + columns.get(0).getValue() +" value -> " + json.toJSONString());
            redisUnits.set("canal"+columns.get(0).getValue(),json.toJSONString());

            logger.info("\n -------------------------------> \n value : "
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
            logger.info("redisDelete key -> " + "canal"+columns.get(0).getValue());
            redisUnits.del("canal"+columns.get(0).getValue());
        }
    }

}
