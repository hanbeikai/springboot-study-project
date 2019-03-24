package com.beikai.springbootthread.test.aboutCreateThread;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadAboutTimerTest
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/3 17:22
 * @Version 1.0
 *  通过定时器创建线程
 **/
public class ThreadAboutTimerTest {

    public static void main(String[] args) {

        // 使用timer 的方式创建线程
        TimerMethod1();

        // 使用Executors.newSingleThreadScheduledExecutor 方式创建线程
        ScheduledExecutor();

        // 使用线程池的方式
        ScheduledThreadPoolExecutor();


    }

    private static void ScheduledThreadPoolExecutor() {
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程池线程开始了...");
            }
        },0,40, TimeUnit.MILLISECONDS); // 0 表示首次执行任务的延时,40表示每次执行任务的间隔时间,MILLISECONDS 代表的是时间单位
    }

    private static void ScheduledExecutor() {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("定时线程开始了 ... ");
            }
        },0,1000, TimeUnit.MILLISECONDS);
    }

  private static void TimerMethod1() {
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("定时器线程开始执行...");
            }
        },0,1000);// 延时0, 周期 1s
    }
}
