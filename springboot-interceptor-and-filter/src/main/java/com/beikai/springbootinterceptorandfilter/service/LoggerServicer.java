package com.beikai.springbootinterceptorandfilter.service;

import com.beikai.springbootinterceptorandfilter.model.LoggerModel;
import com.beikai.springbootinterceptorandfilter.model.RequestLoggerModel;

import java.util.List;

public interface LoggerServicer {

    public void addLogger(LoggerModel loggerModel);

    public void addRequestLogger(RequestLoggerModel loggerModel);

    public List<RequestLoggerModel> getLog(LoggerModel loggerModel);
}
