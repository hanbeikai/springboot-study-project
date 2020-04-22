package com.beikai.springbootmybatisplusdemo.config;

import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/4/22
 * Time: 9:00 上午
 * Description: 配置 druid 数据库监控
 */
@Configuration
public class DruidConfiguration {

    @Bean
    public ServletRegistrationBean druidStatViewServlet() {
        // 注册服务
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        // 设置白名单（为空表示所有的都可以访问，多个ip可以使用逗号隔开）
        servletRegistrationBean.addInitParameter("allow","");
        // ip 黑名单（存在共同时，deny优先于allow）
        servletRegistrationBean.addInitParameter("deny","127.0.0.2");
        // 设置登陆用户名和密码
        servletRegistrationBean.addInitParameter("loginUsername","root");
        servletRegistrationBean.addInitParameter("loginPassword","root");
        servletRegistrationBean.addInitParameter("deny","127.0.0.2");


        return servletRegistrationBean;
    }
}
