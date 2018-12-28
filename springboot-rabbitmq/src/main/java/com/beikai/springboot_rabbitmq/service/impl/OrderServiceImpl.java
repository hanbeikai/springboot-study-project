package com.beikai.springboot_rabbitmq.service.impl;

import com.alibaba.fastjson.JSON;
import com.beikai.springboot_rabbitmq.constant.Constants;
import com.beikai.springboot_rabbitmq.entity.BrokerMessageLog;
import com.beikai.springboot_rabbitmq.entity.OrderModel;
import com.beikai.springboot_rabbitmq.mapper.BrokerMessageLogMapper;
import com.beikai.springboot_rabbitmq.mapper.OrderMapper;
import com.beikai.springboot_rabbitmq.producer.RabbitOrderSender;
import com.beikai.springboot_rabbitmq.service.OrderService;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @ClassName OrderServiceImpl
 * @Description TODO
 * @Author Admin
 * @Date 2018/12/27 23:33
 * @Version 1.0
 **/
@Service
public class OrderServiceImpl implements OrderService {

    private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;

    @Autowired
    private RabbitOrderSender rabbitOrderSender;

    /**
     * 创建 订单发送对象
     * @param orderModel
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    public void createOrder(OrderModel orderModel) throws Exception {
        // 使用当前时间当做订单创建时间
        Date createTime = new Date();
        // 插入业务数据
        try {
            orderMapper.insert(orderModel);
        } catch (Exception e) {
            System.out.println("添加订单信息出错 错误原因是 : " + e);
            logger.error("添加订单信息出错 错误原因是 : " + e);
        }
        // 插入消息记录表数据
        BrokerMessageLog brokerMessageLog = new BrokerMessageLog();
        brokerMessageLog.setMessageId(orderModel.getMessageId());
        // order对象 装换成 json对象
        brokerMessageLog.setMessage(JSON.toJSONString(orderModel));
        brokerMessageLog.setStatus("0");
        brokerMessageLog.setNextRetry(DateUtils.addMinutes(createTime, Constants.ORDER_TIMEOUT));

        brokerMessageLog.setCreateTime(new Date());
        brokerMessageLog.setUpdateTime(new Date());

        try {
            brokerMessageLogMapper.insert(brokerMessageLog);
        } catch (Exception e) {
            System.out.println("添加消息日志信息出错 错误原因是 : " + e);
            logger.error("添加消息日志信息出错 错误原因是 : " + e);
        }

        try {
            rabbitOrderSender.send(orderModel);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("发送消息出错,错误原因是 : " + e);
        }
    }
}
