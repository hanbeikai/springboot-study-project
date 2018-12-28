package com.beikai.springboot_rabbitmq.constant;

/**
 * @ClassName Constants
 * @Description TODO
 * @Author Admin
 * @Date 2018/12/27 23:15
 * @Version 1.0
 *
 * 这是一个常亮类
 **/
public final class Constants {
    /**
     * 发送中
     */
    public static final String ORDER_SENDING = "0";
    /**
     * 发送成功
     */
    public static final String ORDER_SEND_SUCCESS = "1";
    /**
     * 发送失败
     */
    public static final String ORDER_SEND_FAILURE = "2";
    /**
     * 超时单位 分钟 min
     */
    public static final int ORDER_TIMEOUT = 1;
}
