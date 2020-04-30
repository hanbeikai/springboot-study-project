package com.beikai.MutilOfDruid.scheduler;/**
 * Created with IntelliJ IDEA.
 * User: beikai
 * Date: 2020/4/30
 * Time: 16:41
 * Description:
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName:
 * @Description:
 * @author hbk
 * @date 2020/4/30 16:41
 */
@Component
@Configurable
@EnableScheduling
@EnableAsync
@Slf4j
public class SchedulerDemo {

    @Async
    @Scheduled(fixedDelay = 1000,initialDelay = 1000)
    public void demo1() throws InterruptedException {
        System.out.println("-----------------------------<开始执行定时任务1>-----------------------------");
        System.out.println("执行任务逻辑");
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(20000);

        System.out.println("-----------------------------<结束执行定时任务1>-----------------------------");
    }

   // @Scheduled(fixedDelay = 1000,initialDelay = 1000)
    public void demo2() throws InterruptedException {
        System.out.println("-----------------------------<开始执行定时任务2>-----------------------------");

        System.out.println("执行任务逻辑");
        //Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName());

        System.out.println("-----------------------------<结束执行定时任务2>-----------------------------");
    }

    //@Scheduled(fixedDelay = 1000,initialDelay = 1000)
    public void demo3() throws InterruptedException {
        System.out.println("-----------------------------<开始执行定时任务3>-----------------------------");

        System.out.println("执行任务逻辑");
        //Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName());
        System.out.println("-----------------------------<结束执行定时任务3>-----------------------------");
    }
}
