package com.beikai.springboot_rabbitmq.service;

import com.beikai.springboot_rabbitmq.entity.OrderModel;

/**
 * 订单 业务层接口
 */
public interface OrderService {

    public void createOrder(OrderModel orderModel) throws Exception;

}
