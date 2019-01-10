package com.beikai.springbootinterceptorandfilter.handle;

import com.beikai.springbootinterceptorandfilter.exception.MyException;
import com.beikai.springbootinterceptorandfilter.model.APIResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName GlobalExceptionHandler
 * @Description TODO
 * @Author Admin
 * @Date 2019/1/10 23:05
 * @Version 1.0
 *  自定义全局异常类
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public APIResponseModel errorMsg(HttpServletRequest request,Exception ex){
        logger.error("错误链接"+request.getRequestURL().toString());
        ex.printStackTrace();

        APIResponseModel apiResponseModel = new APIResponseModel();
        apiResponseModel.setCode(MyException.FAIL);
        apiResponseModel.setData("null");
        apiResponseModel.setMessage("系统异常,原因是 : " + ex);
        return apiResponseModel;
    }
}
