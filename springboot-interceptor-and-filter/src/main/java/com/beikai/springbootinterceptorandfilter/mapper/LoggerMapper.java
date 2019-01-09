package com.beikai.springbootinterceptorandfilter.mapper;

import com.beikai.springbootinterceptorandfilter.model.LoggerModel;
import com.beikai.springbootinterceptorandfilter.model.RequestLoggerModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface LoggerMapper {
    public void addLogger(LoggerModel loggerModel);

    public void addRequestLogger(RequestLoggerModel loggerModel);

    public List<RequestLoggerModel> getLog(LoggerModel loggerModel);
}
