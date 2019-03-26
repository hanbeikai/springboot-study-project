package com.beikai.springboottestdemo.aspect.test;

import com.beikai.springboottestdemo.aspect.config.AopConfiguration;
import com.beikai.springboottestdemo.aspect.service.AopService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName Test
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/26 21:05
 * @Version 1.0
 * <p>
 * 测试类
 **/
public class Test {
    public static void main(String[] args) {
        // 初始化 bean
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AopConfiguration.class);
        // context.getBean()的底层就是 map.get()方法 DefaultSingletonBeanRegistry 类下  getSingleton() 方法中
        // 代理对象不是在get的时候代理的,而是在init的时候完成的
        context.getBean(AopService.class).query();
    }
}
