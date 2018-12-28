package com.beikai.springboot_rabbitmq.test;

import com.beikai.springboot_rabbitmq.entity.OrderModel;
import com.beikai.springboot_rabbitmq.producer.OrderSender;
import com.beikai.springboot_rabbitmq.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @ClassName OrderSendTest
 * @Description TODO
 * @Author Admin
 * @Date 2018/12/25 21:49
 * @Version 1.0
 *  这是 测试 是否发送成功的 类
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderSendTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderSendTest.class);

    @Autowired
    private OrderSender orderSender;

    @Autowired
    private OrderService orderService;

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

    @Test
    public void RabbitmqSendTest() throws Exception {
        OrderModel orderModel = new OrderModel();
        orderModel.setId("2018122500001");
        orderModel.setName("测试发送数据");
        orderModel.setMessageId(System.currentTimeMillis()+"$"+ UUID.randomUUID().toString());

        orderService.createOrder(orderModel);

    }
}
