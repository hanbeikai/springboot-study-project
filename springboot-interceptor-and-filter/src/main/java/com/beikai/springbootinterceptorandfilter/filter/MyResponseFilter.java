package com.beikai.springbootinterceptorandfilter.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.beikai.springbootinterceptorandfilter.mapper.LoggerMapper;
import com.beikai.springbootinterceptorandfilter.model.RequestLoggerModel;
import com.beikai.springbootinterceptorandfilter.rabbitmq.producer.MessageProducter;
import com.beikai.springbootinterceptorandfilter.util.DateUtils;
import com.beikai.springbootinterceptorandfilter.wrapper.MyHttpServletResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * @ClassName MyResponseFilter
 * @Description TODO
 * @Author Admin
 * @Date 2019/1/9 21:45
 * @Version 1.0
 * <p>
 * 自定义响应 拦截器
 * *  @WebFilter (filterName = "MyResponseFilter",urlPatterns = "/*")
 * *      自动注入当前类到spring容器的 注解
 * *
 * *      filterName 拦截器类名
 * *      urlPatterns 过滤路径
 **/
public class MyResponseFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(MyResponseFilter.class);

    @Autowired
    private LoggerMapper loggerMapper;

    @Autowired
    private MessageProducter messageProducter;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("--------------------MyResponseFilter过滤器 初始化----------------------------");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        logger.info("--------------------MyResponseFilter过滤器 开始执行----------------------------");

        MyHttpServletResponseWrapper wrapper = new MyHttpServletResponseWrapper((HttpServletResponse) response);
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        filterChain.doFilter(request, wrapper);

        RequestLoggerModel requestLoggerModel = (RequestLoggerModel) httpServletRequest.getAttribute("LoggerRecordInterceptor_model");

        if (null == requestLoggerModel){
            requestLoggerModel = new RequestLoggerModel();
            requestLoggerModel.setResponseParams("请求方法输入错误,没有触发请求....");
        }
        try {

            // 获取response返回的内容并重新写入response
            String result = wrapper.getResponseData(response.getCharacterEncoding());
            if (null != result && !"".equals(result) && !(result.contains("<!DOCTYPE html>"))){
                response.getOutputStream().write(result.getBytes());
                requestLoggerModel.setResponseParams(result);
                JSONObject jsonObject = JSON.parseObject(result);
                requestLoggerModel.setResponseCode(jsonObject.get("code").toString());
            }
            requestLoggerModel.setResponseTime(DateUtils.dateFormat(new Date(), DateUtils.HOUR_PATTERN));

            requestLoggerModel.setResponseCode(String.valueOf(wrapper.getStatus()));

            requestLoggerModel.setResponseMessage("日志记录成功!");
        } catch (ParseException e) {
            logger.error("获取响应出错,错误原因是 : " + e);
            requestLoggerModel.setResponseMessage("获取响应出错,错误原因是 : " + e);
        }

        try {

            //解决service为null无法注入问题
            /*if (null == loggerMapper) {
                BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
                loggerMapper = (LoggerMapper) factory.getBean("loggerMapper");
            }*/

            // loggerMapper.addRequestLogger(requestLoggerModel);

            messageProducter.send(MessageProducter.EXCHANGEKEY,MessageProducter.ROUNTINGKEY,requestLoggerModel);

        } catch (Exception e) {
            logger.error("添加请求参数信息出错 , 错误原因是 : " + e);
        }

    }

    @Override
    public void destroy() {
        logger.info("--------------------MyResponseFilter过滤器 销毁----------------------------");
    }
}
