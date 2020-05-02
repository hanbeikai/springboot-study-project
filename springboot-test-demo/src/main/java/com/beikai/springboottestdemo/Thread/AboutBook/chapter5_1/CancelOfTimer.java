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
 * Description: 线程定时器 Cancel() 方法使用
 *
 *   timetask.cancel() 将自身从定时任务队列中移除，其他任务不受影响，但是进程一直存在
 *
 *      字符串时间 ： 2020-5-2 11:52:00 当前时间 ： 2020-5-2 12:02:36
 *      运行了！时间为 ： 2020-05-02T12:02:36.779
 *      运行了任务2 时间为 ： 2020-05-02T12:02:36.780
 *      运行了任务2 时间为 ： 2020-05-02T12:02:37.182
 *      运行了任务2 时间为 ： 2020-05-02T12:02:37.584
 *      运行了任务2 时间为 ： 2020-05-02T12:02:37.989
 *      。。。
 *
 *    timer.cancel() 将任务队列中的所有任务都清空，进程已经不在了
 *      字符串时间 ： 2020-5-2 11:52:00 当前时间 ： 2020-5-2 12:10:23
 *      运行了！时间为 ： 2020-05-02T12:10:23.083
 *      运行了任务2 时间为 ： 2020-05-02T12:10:23.083
 *
 *
 *    注意 ：
 *      1。 如果进程已经别销毁，再调用取消的方法，会被抛异常
 *      2。 timer类的cancel 方法有时并不会被销毁，而是正常执行
 *           因为timer类中的cancel方法有时并没有抢到queue锁，所以timertask中的任务继续正常进行
 *
 */
public class CancelOfTimer {

    public static void main(String[] args) throws ParseException {
        MyTesk myTesk = new MyTesk();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String taskTime = "2020-05-02 11:52:00";
        Date parse = simpleDateFormat.parse(taskTime);
        System.out.println("字符串时间 ： " + parse.toLocaleString() + " 当前时间 ： " + new Date().toLocaleString());
        timer.schedule(myTesk,parse,400);

        MyTask2 myTask2 = new MyTask2();
        timer.schedule(myTask2,parse,400);

        MyTask3 myTask3 = new MyTask3();
        timer.schedule(myTask3,parse,400);
    }

    private static Timer timer = new Timer();
    // 设置成守护线程  线程不会被执行
    //private static Timer timer = new Timer(true);
    private static class MyTesk extends TimerTask {
        @Override
        public void run() {
            System.out.println("运行了！时间为 ： " + LocalDateTime.now());
            this.cancel();
        }
    }

    private static class MyTask2 extends TimerTask{
        @Override
        public void run() {
            System.out.println("运行了任务2 时间为 ： " + LocalDateTime.now());
            timer.cancel();
        }
    }

    private static class MyTask3 extends TimerTask{
        @Override
        public void run() {
            System.out.println("运行了任务3 时间为 ： " + LocalDateTime.now());
        }
    }

}
