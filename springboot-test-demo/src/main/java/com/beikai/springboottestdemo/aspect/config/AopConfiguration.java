package com.beikai.springboottestdemo.aspect.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @ClassName AopConfiguration
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/26 21:04
 * @Version 1.0
 **/
@Configuration
@ComponentScan("com.beikai")
@EnableAspectJAutoProxy
public class AopConfiguration {

}
