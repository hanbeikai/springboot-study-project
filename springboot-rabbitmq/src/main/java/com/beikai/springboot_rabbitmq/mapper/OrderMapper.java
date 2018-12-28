package com.beikai.springboot_rabbitmq.mapper;

import com.beikai.springboot_rabbitmq.entity.OrderModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单 mapper层
 */
@Mapper
public interface OrderMapper {
    /**
     * 新增
     *
     * @param order 订单
     */
    void insert(OrderModel order);
}
