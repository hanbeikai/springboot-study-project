package com.beikai.springboot_rabbitmq.entity;

import java.util.Date;

/**
 * @ClassName BrokerMessageLog
 * @Description TODO
 * @Author Admin
 * @Date 2018/12/27 20:38
 * @Version 1.0
 *
 *  用于保证 消息对象的 可靠性存储到数据的类型
 **/
public class BrokerMessageLog {

    /**
     * 消息主键
     */
    private String messageId;
    /**
     * 消息内容
     */
    private String message;
    /**
     * 重新发送的次数
     */
    private Integer tryCount;
    /**
     * 消息的状态
     */
    private String status;
    /**
     * 下一次尝试发送的时间
     */
    private Date nextRetry;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getTryCount() {
        return tryCount;
    }

    public void setTryCount(Integer tryCount) {
        this.tryCount = tryCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getNextRetry() {
        return nextRetry;
    }

    public void setNextRetry(Date nextRetry) {
        this.nextRetry = nextRetry;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
