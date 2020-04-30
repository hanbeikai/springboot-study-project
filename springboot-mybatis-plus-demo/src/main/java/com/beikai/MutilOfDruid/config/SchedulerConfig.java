package com.beikai.MutilOfDruid.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2019/10/21
 * Time: 3:47 下午
 * Description: 配置定时器
 */
@Configuration
public class SchedulerConfig implements SchedulingConfigurer {

    /**
     * 自定义 定时任务线程池
     * @param scheduledTaskRegistrar
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setTaskScheduler(threadPoolTaskScheduler());
    }

    @Bean(destroyMethod = "shutdown")
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        // 设置线程前缀
        threadPoolTaskScheduler.setThreadNamePrefix("user-info-thread: ");
        // 设置线程池大小
        threadPoolTaskScheduler.setPoolSize(10);
        //
        threadPoolTaskScheduler.setAwaitTerminationSeconds(60);
        // 设置是否等待任务完成后再关闭任务
        threadPoolTaskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        return threadPoolTaskScheduler;
    }
}
