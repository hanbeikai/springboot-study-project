package com.beikai.springbootinterceptorandfilter.config.webconfig;

import com.beikai.springbootinterceptorandfilter.interceptor.LoggerRecordInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName MyWebMvcConfig
 * @Description TODO
 * @Author Admin
 * @Date 2019/1/3 23:12
 * @Version 1.0
 *  自定义 web 适配器  配置拦截器  处理静态资源  配置消息转换等
 **/
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoggerRecordInterceptor loggerRecordInterceptor;

    /**
     * 注册拦截器
     * @param registry  拦截器注册中心
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 LoggerRecordInterceptor 系统操作日志拦截器(凡是加了自定义注解的方法都会添加用户操作日志) , 拦截所有请求
        registry.addInterceptor(loggerRecordInterceptor).addPathPatterns("/log/**"); // 拦截器里有 @autowire或者 @resource 注入的属性就这么用
        // 注册 LoggerRecordInterceptor 系统请求拦截器(不拦截登录 注销 回调请求 其他都拦截)
        /*registry.addInterceptor(new LoggerRecordInterceptor()).addPathPatterns("/**");
        // 连接器里没有@autowire或者@resource注入的属性就这么用
        super.addInterceptor(registry);*/
    }

}
