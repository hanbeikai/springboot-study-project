package com.beikai.springboot_rabbitmq.config.timingtask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.*;

/**
 * 标识 这是一个配置文件
 */
@Configuration
/**
 *  这个注解的作用是 启用定时任务
 */
@EnableScheduling
/**
 * @ClassName TaskSchedulerConfig
 * @Description TODO
 * @Author Admin
 * @Date 2018/12/27 22:38
 * @Version 1.0
 *  定时任务 配置 文件
 **/
public class TaskSchedulerConfig implements SchedulingConfigurer {

    private static Logger logger = LoggerFactory.getLogger(TaskSchedulerConfig.class);

    /**
     * 重写 configureTasks 方法  重写这个方法的目的是为了创建线程池
     * @param scheduledTaskRegistrar
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        //scheduledTaskRegistrar.setScheduler(getTaskSchedulerPool());
        scheduledTaskRegistrar.setScheduler(taskScheduler());
    }

    /**
     * 创建 创建线程池的方法
     */
   /* @Bean(destroyMethod = "shutdown")
    public Executor getTaskSchedulerPool(){
        ThreadPoolExecutor executor = null;
        try {
            executor = new ThreadPoolExecutor(10,
                    100,60, TimeUnit.SECONDS,
                    new LinkedBlockingQueue<Runnable>(),new MyThread(),new ThreadPoolExecutor.AbortPolicy());
            logger.info("TaskSchedulerConfig 创建了一个线程 ");
        } catch (Exception e) {
            logger.error("TaskSchedulerConfig 创建线程失败 , 失败的原因是 : " + e);
        }
        return executor;
    }*/

    @Bean(destroyMethod="shutdown")
    public Executor taskScheduler(){
        logger.info("taskScheduler 创建了一个线程 ");
        return Executors.newScheduledThreadPool(100);
    }
}
