package com.beikai.springbootinterceptorandfilter.service.impl;

import com.beikai.springbootinterceptorandfilter.mapper.LoggerMapper;
import com.beikai.springbootinterceptorandfilter.model.LoggerModel;
import com.beikai.springbootinterceptorandfilter.model.RequestLoggerModel;
import com.beikai.springbootinterceptorandfilter.service.LoggerServicer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName LoggerServiceImpl
 * @Description TODO
 * @Author Admin
 * @Date 2019/1/3 23:03
 * @Version 1.0
 **/
@Service
public class LoggerServiceImpl implements LoggerServicer {

    @Autowired
    private LoggerMapper loggerMapper;


    @Override
    public void addLogger(LoggerModel loggerModel) {
        loggerMapper.addLogger(loggerModel);
    }

    @Override
    public void addRequestLogger(RequestLoggerModel loggerModel) {
        loggerMapper.addRequestLogger(loggerModel);
    }

    @Override
    public List<RequestLoggerModel> getLog(LoggerModel loggerModel) {
        return loggerMapper.getLog(loggerModel);
    }
}
