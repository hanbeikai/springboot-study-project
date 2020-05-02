package com.beikai.springboottestdemo.Thread.AboutBook.chapter5_1;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/2
 * Time: 3:07 下午
 * Description:  void schedule(TimerTask task, long delay, long period)
 * 以执行方法当前的时间作为参考时间，
 * 再次基础上延迟指定的时间后再次执行task任务,
 * 然后每隔指定时间重复执行一次
 *
 * 结果：
 *  当前时间为    ： 1588404350154
 *  运行了，时间为 ： 1588404352157
 *  运行了，时间为 ： 1588404356161
 *  运行了，时间为 ： 1588404360164
 *  运行了，时间为 ： 1588404364166
 *  。。。
 *
 *
 */
public class ScheduleOfTimer4 {

    static private class MyTask extends TimerTask{

        @Override
        public void run() {
            System.out.println("运行了，时间为 ： " + System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Timer timer = new Timer();
        System.out.println("当前时间为    ： " + System.currentTimeMillis());
        // 指定任务在延时多久后执行，并每隔多久执行一次
        timer.schedule(myTask,2000,4000);
    }

}
