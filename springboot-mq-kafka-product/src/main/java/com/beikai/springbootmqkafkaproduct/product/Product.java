package com.beikai.springbootmqkafkaproduct.product;

import com.alibaba.fastjson.JSONObject;
import com.beikai.springbootmqkafkaproduct.model.UserLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2019/10/14
 * Time: 5:20 下午
 * Description: No Description
 */
@Component
public class Product {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void sendLog(Long userId){

        UserLog userLog = new UserLog()
                .setUserId(userId)
                .setUsername("张三")
                .setAge(12);

        String userMsg = JSONObject.toJSONString(userLog);

        System.out.println("发送用户数据 -> " + userMsg);

        kafkaTemplate.send("user-log",userMsg);

    }

}
