package com.beikai.springboottestdemo.Thread.AboutBook.chapter5_1;

import lombok.SneakyThrows;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/2
 * Time: 3:30 下午
 * Description:
 * <p>
 * schedule 和 scheduleAtFixedRate 的区别
 * 共同点
 * 两者都是顺序执行的定时任务，所以不用考虑非线程安全的问题
 * 不同点
 * 主要在执行的任务不发生延时的情况有不同，
 * 因为如果发生延时，任务都是在上一次任务执行结束后再执行
 * <p>
 * 如果不发生延时，
 * schedule 执行是在上一次任务执行开始的时间计算延时时间
 * scheduleAtFixedRate 执行的是在上一次任务执行结束的时候计算延时时间
 */
public class ScheduleAtFixedRateOfTimer5 {

    private static Timer timer = new Timer();
    private static int runCount = 0;

    private static class MyTask extends TimerTask {

        private long time;

        public MyTask(long time) {
            this.time = time;
        }

        @SneakyThrows
        @Override
        public void run() {
            System.out.println("begin time : " + System.currentTimeMillis());
            Thread.sleep(time);
            System.out.println("end   time : " + System.currentTimeMillis());
            runCount++;
            if (runCount == 5) {
                timer.cancel();
            }
        }
    }

    public static void main(String[] args) throws ParseException {
        // 测试schedule 方法任务不延时
        //testTaskNoDelay(1000L);

        // 测试schedule 方法任务延时
        //testTaskDelay(5000L);

        // 测试 scheduleAtFixedRate 方法不延时
        // testScheduleAtFixedRateNoDelay(1000);

        // 测试 scheduleAtFixedRate 方法延时
        testScheduleAtFixedRateDelay(5000);

    }

    // 方法延时  延时任务启动时间以上一次任务结束时间为准
    private static void testScheduleAtFixedRateDelay(long time) throws ParseException {
        MyTask myTask = new MyTask(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String tasktime = "2020-05-02 14:04:00";
        Date parse = simpleDateFormat.parse(tasktime);
        System.out.println("字符串时间 ： " + parse.toLocaleString() + "  当前时间 ： " + new Date().toLocaleString());

        // 执行定时任务
        timer.scheduleAtFixedRate(myTask, parse, 3000);

        /** 延时的情况下，下一次的任务是上一次任务的结束时间
         *  字符串时间 ： 2020-5-2 14:04:00  当前时间 ： 2020-5-2 16:50:32
         * begin time : 1588409432858
         * end   time : 1588409437861
         * begin time : 1588409437861
         * end   time : 1588409442864
         * begin time : 1588409442865
         */
    }

    // 方法不延时
    private static void testScheduleAtFixedRateNoDelay(long time) throws ParseException {
        MyTask myTask = new MyTask(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String tasktime = "2020-05-02 14:04:00";
        Date parse = simpleDateFormat.parse(tasktime);
        System.out.println("字符串时间 ： " + parse.toLocaleString() + "  当前时间 ： " + new Date().toLocaleString());

        // 执行定时任务
        timer.scheduleAtFixedRate(myTask, parse, 4000);

        /**
         *  不延时的情况下 上一次任务的结束时间加上延时时间作为下一次任务的开始时间（由于scheduleAtFixedRate方法 '追赶性' 特点，会首先补全
         *  设置的定时时间点与当前时间之间任务的次数，所以会出现上一次执行结束时间直接是下一次执行开始的时间，当差的
         *  执行次数补全后，会按照上一次任务的结束时间加上延时时间作为下一次任务的开始时间）
         * 字符串时间 ： 2020-5-2 14:04:00  当前时间 ： 2020-5-2 16:41:35
         * begin time : 1588408895399
         * end   time : 1588408896400
         * begin time : 1588408896400
         * end   time : 1588408897403
         * begin time : 1588408897403
         */
    }

    // 如果执行的计划被延时了，下一次任务开始的时间是以上一次任务的结束时间为准
    private static void testTaskDelay(long time) throws ParseException {
        MyTask myTask = new MyTask(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String tasktime = "2020-05-02 14:04:00";
        Date parse = simpleDateFormat.parse(tasktime);
        System.out.println("字符串时间 ： " + parse.toLocaleString() + "  当前时间 ： " + new Date().toLocaleString());

        // 执行定时任务
        timer.schedule(myTask, parse, 3000);

        /** 延时的情况下，下一次任务的开始时间是上一次任务的结束时间
         * 字符串时间 ： 2020-5-2 14:04:00  当前时间 ： 2020-5-2 16:40:06
         * begin time : 1588408806396
         * end   time : 1588408811401
         * begin time : 1588408811402
         * end   time : 1588408816405
         * begin time : 1588408816405
         */
    }


    // 方法不延时，任务中的睡眠时间小于延时等待时间
    private static void testTaskNoDelay(long time) throws ParseException {
        MyTask myTask = new MyTask(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String taskTime = "2020-05-02 15:37:00";
        Date parse = simpleDateFormat.parse(taskTime);
        System.out.println("字符串时间 ： " + parse.toLocaleString() + "  当前时间 ： " + new Date().toLocaleString());

        // 执行任务
        timer.schedule(myTask, parse, 4000);

        /**
         *  不延时的情况下，下一次执行任务的时间是上一次执行任务的开始时间
         *  字符串时间 ： 2020-05-02 15:37:00  当前时间 ： 2020-5-2 15:40:22
         *  begin time : 1588405225830
         *  end   time : 1588405226833
         *
         *  begin time : 1588405229834
         *  end   time : 1588405230836
         *  begin time : 1588405233835
         *  end   time : 1588405234838
         *  begin time : 1588405237839
         *  。。。
         *
         */

    }

}
