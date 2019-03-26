package com.beikai.springboottestdemo.aspect.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @ClassName AopConfig
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/26 20:55
 * @Version 1.0
 *  定义的切面
 **/
@Aspect
@Component
public class AopConfig {

    /**
     * 设置切点
     */
    @Pointcut("execution(* com.beikai.springboottestdemo.aspect.service..*.*(..))")
    public void pointcut(){

    }

    /**
     * 在切点之前执行的代码
     */
    @Before("pointcut()")
    public void before(){
        System.out.println("start ---------------");
    }



}
