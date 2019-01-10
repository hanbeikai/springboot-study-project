package com.beikai.springbootinterceptorandfilter.exception;

/**
 * @ClassName MyException
 * @Description TODO
 * @Author Admin
 * @Date 2019/1/10 23:11
 * @Version 1.0
 *  自定义 异常类
 **/
public class MyException extends Exception{
    /**
     * 成功代码
     */
    public static final String SUCCESS = "10000";
    /**
     * 失败代码
     */
    public static final String FAIL = "20000";
    /**
     * 异常代码
     */
    private String code;

    public MyException(String message,String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
