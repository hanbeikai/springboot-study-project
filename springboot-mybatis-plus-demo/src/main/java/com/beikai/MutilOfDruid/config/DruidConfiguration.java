package com.beikai.MutilOfDruid.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/4/22
 * Time: 9:00 上午
 * Description: 配置 druid 数据库监控
 */
@Configuration
@Slf4j
public class DruidConfiguration {

    @Bean
    public ServletRegistrationBean druidStatViewServlet() {
        // 注册服务
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
//        // 设置白名单（为空表示所有的都可以访问，多个ip可以使用逗号隔开）
//        servletRegistrationBean.addInitParameter("allow","");
//        // ip 黑名单（存在共同时，deny优先于allow）
//        servletRegistrationBean.addInitParameter("deny","127.0.0.2");
//        // 设置登陆用户名和密码
//        servletRegistrationBean.addInitParameter("loginUsername","root");
//        servletRegistrationBean.addInitParameter("loginPassword","root");
//        servletRegistrationBean.addInitParameter("resetEnable","false");

        Map<String,String> params = Maps.newHashMap();
        // 设置白名单（为空表示所有的都可以访问，多个ip可以使用逗号隔开）
        params.put("allow","");
        // ip 黑名单（存在共同时，deny优先于allow）
        params.put("deny","127.0.0.2");
        // 设置登陆用户名和密码
        params.put("loginUsername","root");
        params.put("loginPassword","root");
        // 是否能够重置数据
        params.put("resetEnable","false");

        servletRegistrationBean.setInitParameters(params);

        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean druidStatFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        // 添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        // 添加不需要忽略的格式信息
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        log.info("-----------------< druid初始化成功! >-----------------");

        return filterRegistrationBean;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }
}
