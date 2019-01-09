package com.beikai.springbootinterceptorandfilter.config.filterconfig;

import com.beikai.springbootinterceptorandfilter.filter.MyResponseFilter;
import com.beikai.springbootinterceptorandfilter.filter.Myfilter;
import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MyWebConfiguration
 * @Description TODO
 * @Author Admin
 * @Date 2019/1/5 19:24
 * @Version 1.0
 *   配置 springboot 中一些配置文件
 **/
@Configuration
public class MyWebConfiguration {

    @Bean
    public RemoteIpFilter remoteIpFilter(){
        return new RemoteIpFilter();
    }

    /**
     * 配置过滤器 注入到spring容器
     * @return
     */
    @Bean
    public FilterRegistrationBean loggerFilterRegistration(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        // 添加过滤器
        registrationBean.setFilter(new Myfilter());
        //添加过滤路径 /* 代表所有路径
        registrationBean.addUrlPatterns("/log/*");
        // 添加默认参数
        //registrationBean.addInitParameter("name","value");
        //设置过滤器名
        registrationBean.setName("Myfilter");
        //设置优先级  如果有多个 自定义过滤器 通过这个设置过滤器的先后顺序
        registrationBean.setOrder(1);
        return registrationBean;
    }

    /**
     * 配置过滤器 注入到spring容器
     * @return
     */
    @Bean
    public FilterRegistrationBean loggerFilterRegistration2(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        // 添加过滤器
        registrationBean.setFilter(new MyResponseFilter());
        //添加过滤路径 /* 代表所有路径
        registrationBean.addUrlPatterns("/log/*");
        // 添加默认参数
        //registrationBean.addInitParameter("name","value");
        //设置过滤器名
        registrationBean.setName("MyResponseFilter");
        //设置优先级  如果有多个 自定义过滤器 通过这个设置过滤器的先后顺序
        registrationBean.setOrder(2);
        return registrationBean;
    }
}
