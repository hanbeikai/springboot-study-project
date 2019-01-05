package com.beikai.springbootinterceptorandfilter.mapper;

import com.beikai.springbootinterceptorandfilter.model.LoggerModel;
import com.beikai.springbootinterceptorandfilter.model.RequestLoggerModel;

import java.util.List;

public interface LoggerMapper {
    public void addLogger(LoggerModel loggerModel);

    public void addRequestLogger(RequestLoggerModel loggerModel);

    public List<RequestLoggerModel> getLog(LoggerModel loggerModel);
}
