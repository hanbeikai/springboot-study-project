package com.beikai.dubbo;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * @ClassName DubboConfig
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/4 20:39
 * @Version 1.0
 **/
@Configuration
@PropertySource("classpath:dubbo/dubbo.properties")
@ImportResource({"classpath:dubbo/*.xml"})
public class DubboConfig {
}
