package com.beikai.springboot_rabbitmq;

import com.beikai.springboot_rabbitmq.entity.OrderModel;
import com.beikai.springboot_rabbitmq.producer.OrderSender;
import com.beikai.springboot_rabbitmq.test.OrderSendTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitmqApplicationTests {

    @Test
    public void contextLoads() {
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderSendTest.class);

    @Autowired
    private OrderSender orderSender;

    @Test
    public void sendTest() throws Exception {
        OrderModel orderModel = new OrderModel();
        orderModel.setId("2018122500001");
        orderModel.setName("测试发送数据");
        orderModel.setMessageId(System.currentTimeMillis()+"$"+ UUID.randomUUID().toString());

        try {
            orderSender.send(orderModel);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("发送消息出错,错误原因是 : " + e);
        }
    }


}

