package com.beikai.springbootmqkafkaconsumer.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2019/10/14
 * Time: 5:30 下午
 * Description: No Description
 */
@Component
@Slf4j
public class Consumer {

    @KafkaListener(topics = {"user-log"})
    public void consumer(ConsumerRecord<?,?> consumerRecord){

        // 判断是否为null
        Optional<?> kafkaMessage = Optional.ofNullable(consumerRecord.value());
        log.info(">>>>>>>>>>>> record = " + kafkaMessage);
        if (kafkaMessage.isPresent()) {
            // 得到 optional 中的值
            Object message = kafkaMessage.get();
            System.out.println("消息 --》 " + message);
        }

    }

}
