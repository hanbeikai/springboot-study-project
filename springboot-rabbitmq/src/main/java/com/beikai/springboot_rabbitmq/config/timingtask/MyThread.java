package com.beikai.springboot_rabbitmq.config.timingtask;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.concurrent.ThreadFactory;

/**
 * @ClassName MyThread
 * @Description TODO
 * @Author Admin
 * @Date 2018/12/27 23:04
 * @Version 1.0
 *  自定义 线程
 **/
@Component
public class MyThread implements ThreadFactory {

    @Override
    public Thread newThread(@NotNull Runnable r) {
        Thread thread = new Thread(r);
        thread.setName("scheduleThread");
        return thread;
    }
}
