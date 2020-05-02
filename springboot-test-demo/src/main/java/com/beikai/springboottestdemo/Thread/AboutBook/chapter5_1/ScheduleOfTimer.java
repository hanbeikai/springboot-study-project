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
 *   void schedule(TimerTask task, Date time)  指定定时任务在指定时间开始
 *
 *   注意 ：
 *      如果使用new Timer()构造生成的定时器 在执行完定时任务后进程还未销毁，
 *
 *   解决的办法是 ：
 *      设置成守护线程
 *      private static Timer timer = new Timer(true);
 *      使用守护线程后，timetask 任务不会再被运行，因为进程已经被杀死了，所以定时任务并未被执行
 *
 *
 *   timer 多任务定时器
 *      通过创建不同的task 来实现不同的定时任务
 *
 *      字符串时间 ： 2020-5-2 11:46:00 当前时间 ： 2020-5-2 11:44:52
 *      字符串时间 ： 2020-5-2 11:45:00 当前时间 ： 2020-5-2 11:44:52
 *      运行了任务2 时间为 ： 2020-05-02T11:45:00.055
 *      运行了！时间为 ： 2020-05-02T11:46:00.005
 *
 *      由上可以看出，定时任务是以队列的形势一个个的执行的，但是时间会可能出现偏差，如果前一个执行的时间过长，可能会导致后面的执行时间顺延
 *
 */
public class ScheduleOfTimer {

    public static void main(String[] args) throws ParseException {
        MyTesk myTesk = new MyTesk();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String taskTime = "2020-05-02 11:46:00";
        Date parse = simpleDateFormat.parse(taskTime);
        System.out.println("字符串时间 ： " + parse.toLocaleString() + " 当前时间 ： " + new Date().toLocaleString());
        timer.schedule(myTesk,parse);

        MyTask2 myTesk2 = new MyTask2();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String taskTime2 = "2020-05-02 11:45:00";
        Date parse2 = simpleDateFormat2.parse(taskTime2);
        System.out.println("字符串时间 ： " + parse2.toLocaleString() + " 当前时间 ： " + new Date().toLocaleString());
        timer.schedule(myTesk2,parse2);
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
