package com.beikai.springbootinterceptorandfilter.service.impl;

import com.beikai.springbootinterceptorandfilter.mapper.LoggerMapper;
import com.beikai.springbootinterceptorandfilter.service.LoggerServicer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
