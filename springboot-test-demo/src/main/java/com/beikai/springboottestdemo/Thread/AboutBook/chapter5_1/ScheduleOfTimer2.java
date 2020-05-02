package com.beikai.springboottestdemo.Thread.AboutBook.chapter5_1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/2
 * Time: 10:15 上午
 * Description: 线程定时器 scheduler 方法使用
 *   void schedule(TimerTask task, Date time，long period)  指定定时任务在指定时间开始，设置循环执行间隔
 *
 *      字符串时间 ： 2020-5-2 11:52:00 当前时间 ： 2020-5-2 11:51:41
 *      运行了！时间为 ： 2020-05-02T11:52:00.056
 *      运行了！时间为 ： 2020-05-02T11:52:00.405
 *      运行了！时间为 ： 2020-05-02T11:52:00.805
 *      运行了！时间为 ： 2020-05-02T11:52:01.206
 *      ...
 *
 */
public class ScheduleOfTimer2 {

    public static void main(String[] args) throws ParseException {
        MyTesk myTesk = new MyTesk();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String taskTime = "2020-05-02 11:52:00";
        Date parse = simpleDateFormat.parse(taskTime);
        System.out.println("字符串时间 ： " + parse.toLocaleString() + " 当前时间 ： " + new Date().toLocaleString());
        timer.schedule(myTesk,parse,400);
    }

    private static Timer timer = new Timer();
    // 设置成守护线程  线程不会被执行
    //private static Timer timer = new Timer(true);
    private static class MyTesk extends TimerTask {
        @Override
        public void run() {
            System.out.println("运行了！时间为 ： " + LocalDateTime.now());
        }
    }

    private static class MyTask2 extends TimerTask{
        @Override
        public void run() {
            System.out.println("运行了任务2 时间为 ： " + LocalDateTime.now());
        }
    }
}
