package com.beikai.springbootinterceptorandfilter.model;

import java.io.Serializable;

/**
 * @ClassName RequestLoggerModel
 * @Description TODO
 * @Author Admin
 * @Date 2019/1/5 20:18
 * @Version 1.0
 **/
public class RequestLoggerModel implements Serializable {

    private static final long serialVersionUID = -1696084176156750453L;
    /**
     * 主键
     */
    private int id;
    /**
     * 请求ip
     */
    private String requestIp;
    /**
     * 请求时间
     */
    private String requestTime;
    /**
     * 请求方法
     */
    private String requestMethod;
    /**
     * 请求参数
     */
    private String requestParams;
    /**
     * 方法描述
     */
    private String methodDescription;
    /**
     * 请求消息
     */
    private String requestMessage;
    /**
     * 响应代号
     */
    private String responseCode;
    /**
     * 响应参数
     */
    private String responseParams;
    /**
     * 响应信息
     */
    private String responseMessage;
    /**
     * 响应时间
     */
    private String responseTime;

    public String getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseParams() {
        return responseParams;
    }

    public void setResponseParams(String responseParams) {
        this.responseParams = responseParams;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(String requestParams) {
        this.requestParams = requestParams;
    }

    public String getMethodDescription() {
        return methodDescription;
    }

    public void setMethodDescription(String methodDescription) {
        this.methodDescription = methodDescription;
    }
}
