package com.beikai.springboot_rabbitmq.entity;

import java.io.Serializable;

/**
 * @ClassName OrderModel
 * @Description TODO
 * @Author Admin
 * @Date 2018/12/25 20:58
 * @Version 1.0
 *  订单对象  消费端和 生产端都要有相同的model
 **/
public class OrderModel implements Serializable {

    private static final long serialVersionUID = -4130100794008693544L;

    /**
     *订单id
      */
    private String id;
    /**
     * 订单名
     */
    private String name;
    /**
     * 唯一 消息id
     */
    private String messageId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
