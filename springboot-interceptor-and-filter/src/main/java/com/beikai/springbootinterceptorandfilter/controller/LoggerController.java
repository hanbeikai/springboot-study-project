package com.beikai.springbootinterceptorandfilter.controller;

import com.beikai.springbootinterceptorandfilter.service.LoggerServicer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName LoggerController
 * @Description TODO
 * @Author Admin
 * @Date 2019/1/3 23:04
 * @Version 1.0
 **/
@RestController
@RequestMapping("/log")
@Api("日志测试")
public class LoggerController {
    @Autowired
    private LoggerServicer loggerServicer;

    @RequestMapping(value = "/getLog",method = RequestMethod.POST)
    @ApiOperation("获取日志信息 : getLog")
    public void getLog(){

        System.out.println("传递进来的信息为 : " );
    }

}
