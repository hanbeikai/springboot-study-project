package com.beikai.springboottestdemo.Thread.AboutBook.chapter5_1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/2
 * Time: 5:03 下午
 * Description:
 *  验证 方法 schedule 和 scheduleAtFixedRate 是否具备追赶性
 *
 *      追赶性 ： 如果程序执行的时间在设置的定时时间之后，则设定的时间与当前的时间之间的范围内，是否会追加对应的任务数
 *          如果追加，说明所有追赶性
 *          如果不追加，说明没有追赶性
 *
 *
 *
 */
public class ScheduleOfTimer6 {

    private static Timer timer = new Timer();
    private static class  MyTask extends TimerTask{
        @Override
        public void run() {
            System.out.println("task start time  " + new Date().toLocaleString());
            System.out.println("task   end time  " + new Date().toLocaleString());
            System.out.println("---------------------------------------");
        }
    }

    public static void main(String[] args) throws ParseException {
        MyTask myTask = new MyTask();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String tasktime = "2020-05-02 17:16:00";
        Date parse = simpleDateFormat.parse(tasktime);
        System.out.println("字符串时间 ： " + parse.toLocaleString() + "  当前时间 ： " + new Date().toLocaleString());

        // 测试 schedule 方法 是否具有追赶性
        //testSchedule(myTask, parse);

        testScheduleAtFixedRate(myTask,parse);
    }

    private static void testScheduleAtFixedRate(MyTask myTask, Date parse) {
        // 执行定时任务
        timer.scheduleAtFixedRate(myTask,parse,3000);

        /**
         * 字符串时间 ： 2020-5-2 17:16:00  当前时间 ： 2020-5-2 17:16:23
         * task start time  2020-5-2 17:16:23
         * task   end time  2020-5-2 17:16:23
         * ---------------------------------------
         * task start time  2020-5-2 17:16:23
         * task   end time  2020-5-2 17:16:23
         * ---------------------------------------
         * task start time  2020-5-2 17:16:23
         * task   end time  2020-5-2 17:16:23
         * ---------------------------------------
         * task start time  2020-5-2 17:16:23
         * task   end time  2020-5-2 17:16:23
         * ---------------------------------------
         * task start time  2020-5-2 17:16:23
         * task   end time  2020-5-2 17:16:23
         * ---------------------------------------
         * task start time  2020-5-2 17:16:23
         * task   end time  2020-5-2 17:16:23
         * ---------------------------------------
         * task start time  2020-5-2 17:16:23
         * task   end time  2020-5-2 17:16:23
         * ---------------------------------------
         * task start time  2020-5-2 17:16:23
         * task   end time  2020-5-2 17:16:23
         *
         * 补全完成了
         * ---------------------------------------
         * task start time  2020-5-2 17:16:24
         * task   end time  2020-5-2 17:16:24
         * ---------------------------------------
         * task start time  2020-5-2 17:16:27
         * task   end time  2020-5-2 17:16:27
         * ---------------------------------------
         * task start time  2020-5-2 17:16:30
         * task   end time  2020-5-2 17:16:30
         * ---------------------------------------
         * task start time  2020-5-2 17:16:33
         * task   end time  2020-5-2 17:16:33
         * ---------------------------------------
         */
    }


    private static void testSchedule(MyTask myTask, Date parse) {
        // 执行定时任务
        timer.schedule(myTask, parse, 3000);

        /**{
         * 字符串时间 ： 2020-5-2 14:04:00  当前时间 ： 2020-5-2 17:10:02
         * task start time  2020-5-2 17:10:02
         * task end time  2020-5-2 17:10:02
         * task start time  2020-5-2 17:10:05
         * task end time  2020-5-2 17:10:05
         * task start time  2020-5-2 17:10:08
         * task end time  2020-5-2 17:10:08
         * task start time  2020-5-2 17:10:11
         *
         * 从打印来看，可以看出，schedule 是从当前时间开始执行的，所以不具备追赶性
         */
    }
}
