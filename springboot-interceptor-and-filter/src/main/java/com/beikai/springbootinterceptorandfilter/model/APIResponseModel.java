package com.beikai.springbootinterceptorandfilter.model;

/**
 * @ClassName APIResponseModel
 * @Description TODO
 * @Author Admin
 * @Date 2019/1/5 20:49
 * @Version 1.0
 *  统一 相应 对象
 **/
public class APIResponseModel<T> {
    /**
     * 结果码
      */
    private String code;
    /**
     * 返回信息
      */
    private String message;
    /**
     * 返回的数据
      */
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
