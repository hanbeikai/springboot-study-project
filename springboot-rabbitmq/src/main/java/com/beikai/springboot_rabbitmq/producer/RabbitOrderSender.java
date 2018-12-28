package com.beikai.springboot_rabbitmq.producer;

import com.beikai.springboot_rabbitmq.constant.Constants;
import com.beikai.springboot_rabbitmq.entity.OrderModel;
import com.beikai.springboot_rabbitmq.mapper.BrokerMessageLogMapper;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName RabbitOrderSender
 * @Description TODO
 * @Author Admin
 * @Date 2018/12/28 22:42
 * @Version 1.0
 *
 *  保证100%可靠性的 rabbitmq 方案中的 发送端
 **/
@Component
public class RabbitOrderSender {

    /**
     * 注入 rabbitmqtemplate 对象
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 注入 消息日志 mapper层 用于对发送消息的记录
     */
    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;


    /**
     * 对回调参数进行确认
     */
    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {

        // 重写 确认方法
        @Override
        public void confirm(CorrelationData correlationData, boolean b, String s) {
            System.out.println("--------------------------开始对消息回调处理---------------------------");
            System.out.println("回调接收的参数correlationData内容是 : " + correlationData);
            // 设置 消息主键
            String messageId = correlationData.getId();
            // 对回调的状态进行判断
            // 回调成功
            if (b){
                System.out.println("------------------------ 回调成功 ------------------------");
                brokerMessageLogMapper.changeBrokerMessageLogStatus(messageId, Constants.ORDER_SEND_SUCCESS,new Date());
            }else {  // 回调失败  回调失败后进行的操作  重试 或 补偿手段
                System.out.println("------------------------ 回调失败(进行处理) ------------------------");
            }
        }
    };

    /*@Value("${rabbitmq.rabbitOrderSender.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.rabbitOrderSender.routingKey}")
    private String routingKey;*/

    /**
     * 发送消息
     *  第一个参数 : exchange 对象名称
     *  第二个参数 : 路由匹配规则
     *  第三个参数 : 消息内容
     *  第四个参数 : 消息的唯一值  用于消费之接受消息后回调
     */
    public void send(OrderModel orderModel) throws Exception{
        // rabbitmq 设置  回调
        rabbitTemplate.setConfirmCallback(confirmCallback);
        // 获取消息唯一值
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(orderModel.getMessageId());
        // 调用rabbitmq 发送 方法
        //rabbitTemplate.convertAndSend(exchangeName,routingKey,orderModel,correlationData);
        rabbitTemplate.convertAndSend("order-change","order.abcd",orderModel,correlationData);
    }
}
