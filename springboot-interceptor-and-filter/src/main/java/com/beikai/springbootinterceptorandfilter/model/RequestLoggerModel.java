package com.beikai.springbootinterceptorandfilter.model;

/**
 * @ClassName RequestLoggerModel
 * @Description TODO
 * @Author Admin
 * @Date 2019/1/5 20:18
 * @Version 1.0
 **/
public class RequestLoggerModel {

    private int id;
    private String requestIp;
    private String requestTime;
    private String requestMethod;
    private String requestParams;
    private String methodDescription;

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
