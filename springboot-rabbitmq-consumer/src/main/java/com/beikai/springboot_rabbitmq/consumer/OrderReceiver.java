package com.beikai.springboot_rabbitmq.consumer;

import com.beikai.springboot_rabbitmq.entity.OrderModel;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName OrderReceiver
 * @Description TODO
 * @Author Admin
 * @Date 2018/12/25 22:28
 * @Version 1.0
 *  mq 中 消息的接收者
 **/
@Component
public class OrderReceiver {

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
    @RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "order-queue",durable = "true"),
            exchange = @Exchange(name = "order-change",type = "topic"),
            key = "order.*"
    )  )

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
    public void orderReceiver(@Payload OrderModel orderModel,
                              @Headers Map<String,Object> header,
                              Channel channel) throws Exception{

        System.out.println("-------------------收到消息,开始消费------------------------");
        System.out.println("订单ID : " + orderModel.getId());

        /**
         * 从头文件中获取 用于传送回去的唯一值
         */
        Long deliveryTag = (Long) header.get(AmqpHeaders.DELIVERY_TAG);

        /**
         *          ACK
         *         消费完成之后 告诉消息队列 已经消费完成了
         *          第二个参数是设置是否批量操作 false 否
          */
        channel.basicAck(deliveryTag,false);
    }
}
