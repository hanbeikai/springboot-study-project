package com.beikai.springboot_rabbitmq.producer;

import com.beikai.springboot_rabbitmq.entity.OrderModel;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName OrderSender
 * @Description TODO
 * @Author Admin
 * @Date 2018/12/25 21:10
 * @Version 1.0
 *  消息队列中 生产者 提供的订单 发送对象
 **/
@Component
public class OrderSender {

    /**
     * 注入 rabbittemplate 对象
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送 消息对象
     */
    public void send(OrderModel order) throws Exception{
        /**
         * 第一个参数 : exchang 对象
         *  第二个参数 : routingKey 对象
         *  第三个参数 : 消息的内容
         *  第四个参数 : 消息唯一id
         */
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(order.getMessageId());
        rabbitTemplate.convertAndSend("order-change",
                "order.abcd",order,correlationData);
    }
}
