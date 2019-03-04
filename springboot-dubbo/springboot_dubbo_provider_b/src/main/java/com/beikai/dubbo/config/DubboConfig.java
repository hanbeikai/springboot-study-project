package com.beikai.dubbo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * @ClassName DubboConfig
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/4 19:50
 * @Version 1.0
 *
 *  @Configuration  表示当前文件是配置文件
 *  @PropertySource  设置读取dubbo配置文件信息
 *  @ImportResource   设置 读取 dubbo xml 文件信息
 **/
@Configuration
@PropertySource("classpath:dubbo/dubbo.properties")
@ImportResource({"classpath:dubbo/*.xml"})
public class DubboConfig {

}
