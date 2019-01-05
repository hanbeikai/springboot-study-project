package com.beikai.springbootinterceptorandfilter.model;

/**
 * @ClassName MyResponseHandle
 * @Description TODO
 * @Author Admin
 * @Date 2019/1/5 20:53
 * @Version 1.0
 *
 *  返回结果处理类
 **/
public class MyResponseHandle {

    private static APIResponseModel apiResponseModel;

    public static final String SUCCESS = "10000";

    public static final String FAIL = "20000";

    /**
     * 获取相应对象 无数据
     * @param code
     * @param message
     * @return
     */
    public static APIResponseModel getResponse(String code,String message){
        apiResponseModel = new APIResponseModel();
        apiResponseModel.setCode(code);
        apiResponseModel.setMessage(message);

        return apiResponseModel;
    }

    /**
     * 获取相应对象 有数据
     * @param code
     * @param data
     * @return
     */
    public static APIResponseModel getResponseWithData(String code,Object data){
        apiResponseModel = new APIResponseModel();
        apiResponseModel.setCode(code);
        apiResponseModel.setData(data);

        return apiResponseModel;
    }
}
