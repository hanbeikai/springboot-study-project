package com.beikai.springbootinterceptorandfilter.rabbitmq.producer;

import com.beikai.springbootinterceptorandfilter.model.RequestLoggerModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName MessageProducter
 * @Description TODO
 * @Author Admin
 * @Date 2019/1/14 22:05
 * @Version 1.0
 *  消息队列中 消息的生产者
 **/
@Component
public class MessageProducter {
    /**
     * 声明 日志
     */
    private static Logger logger = LoggerFactory.getLogger(MessageProducter.class);

    /**
     * 声明 rountingkey
     */
    public static final String ROUNTINGKEY = "mq.logger.*";

    public static final String EXCHANGEKEY = "mq_log_queue";

    /**
     * 注入 rabbittemplate
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 创建发送消息的方法
     */
    public void send(String exchangeKey,String rountintKey, RequestLoggerModel object){

        logger.info("-----------------消息发送开始--------------------------");
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(String.valueOf(object.getId()));

        try {
            rabbitTemplate.convertAndSend(exchangeKey,rountintKey,object,correlationData);
            //rabbitTemplate.convertAndSend(exchangeKey,rountintKey,object);
        } catch (AmqpException e) {
            logger.error("发送消息失败 ,失败的原因是 : " + e);
        }

        logger.info("-----------------消息发送成功--------------------------");

    }
}
