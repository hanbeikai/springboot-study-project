package com.beikai.springbootinterceptorandfilter.rabbitmq.customer;

import com.beikai.springbootinterceptorandfilter.mapper.LoggerMapper;
import com.beikai.springbootinterceptorandfilter.model.RequestLoggerModel;
import com.beikai.springbootinterceptorandfilter.rabbitmq.producer.MessageProducter;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @ClassName MessageCustomer
 * @Description TODO
 * @Author Admin
 * @Date 2019/1/14 22:16
 * @Version 1.0
 *  mq 消息接受者
 **/
@Component
public class MessageCustomer {

    private static Logger logger = LoggerFactory.getLogger(MessageCustomer.class);

    @Autowired
    private LoggerMapper loggerMapper;

    /**
     * 添加 监听具体的exchange 和 bindingkey 如果mq中没有这个exchange 和 queue 会自动创建
     * @Queue 设置监听的queue 如果mq 中没有这个queue 则创建  durable 意思为设置持久化
     * @Exchange 设置监听的exchange 如果mq 中没有这个exchange 则创建  durable 意思为设置持久化  type 设置exchange的类型
     * key  为设置 路由key 的 值
     *
     *
     * 如果监听出现 无法序列化 而导致报错  org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException: Listener threw
     *  Caused by: org.springframework.amqp.support.converter.MessageConversionException: failed to convert serialized Message content
     *  原因是 实体类对应的package 名和  序列化id 不同  这两者要保持相同
     *
     */
    @RabbitListener(bindings = @QueueBinding( value = @Queue(value = MessageProducter.EXCHANGEKEY,durable = "true"),
            exchange = @Exchange(name = MessageProducter.EXCHANGEKEY,type = "topic"),key = MessageProducter.ROUNTINGKEY
    ))

    /**
     * @param orderModel
     * @param header
     * @param channel
     * @throws Exception
     *
     * @RabbitHandler 这个注解是用于标识对应的方法去监听消息队列
     *
     * @Payload  定义消息的主体内容的
     *
     * @Headers  定义 消息的头内容的
     *
     * Channel  如果 配置中设置签收是手动的 则需要设置一个channel
     *
     */
    @RabbitHandler
    public void messageReceiver(
            @Payload RequestLoggerModel object,
            @Headers Map<String ,Object> header,
            Channel channel
            ) throws IOException {
        System.out.println("-------------------收到消息,开始消费------------------------");
        Long id = (Long) header.get(AmqpHeaders.DELIVERY_TAG);

        try {
            loggerMapper.updateRequestLogger(object);
        } catch (Exception e) {
            logger.error("添加请求参数信息出错 , 错误原因是 : " + e);
        }

        /**
         *          ACK
         *         消费完成之后 告诉消息队列 已经消费完成了
         *          第二个参数是设置是否批量操作 false 否
         */
        try {
            channel.basicAck(id,false);
        } catch (IOException e) {
            logger.error("设置消息返回出现错误 错误原因是 : " + e);
        }

        logger.info("-----------------消费成功------------------------------");
    }
}
