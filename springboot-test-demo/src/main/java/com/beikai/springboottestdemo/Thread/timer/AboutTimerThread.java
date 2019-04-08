package com.beikai.springboottestdemo.Thread.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @ClassName AboutTimerThread
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/7 22:25
 * @Version 1.0
 *  定时的线程 测试类
 **/
public class AboutTimerThread {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("-------------start--------------");

        // 创建一个时间对象
        Timer timer = new Timer();

        // 在指定的时间执行
        //timer.schedule(task,time);

        // 延迟指定的时间执行
        //timer.schedule(task,delay);

        // 指定的时间开始,然后每隔指定时间执行
        //timer.schedule(taks,firstTime,period);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(new Date());
            }
        },3000,1000);


        // 主线程将睡眠
        Thread.sleep(10000);
        System.out.println("---------end---------");
    }

}
