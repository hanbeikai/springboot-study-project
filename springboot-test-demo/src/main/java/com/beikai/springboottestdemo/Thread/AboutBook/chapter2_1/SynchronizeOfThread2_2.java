package com.beikai.springboottestdemo.Thread.AboutBook.chapter2_1;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/13
 * Time: 8:06 上午
 * Description: No Description
 */
public class SynchronizeOfThread2_2 {

    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        Thread1 t = new Thread1(task);
        t.start();

        Thread2 t2 = new Thread2(task);
        t2.start();

        Thread.sleep(10000);

        long startTime = CommentUtils.beginTime1;
        if (CommentUtils.beginTime2 < CommentUtils.beginTime1) {
            startTime = CommentUtils.beginTime2;
        }

        long endTime = CommentUtils.endTime1;
        if (CommentUtils.endTime2 > CommentUtils.endTime1) {
            endTime = CommentUtils.endTime2;
        }

        System.out.println("耗时为 : " + ((endTime - startTime) / 1000));
    }
}

class Task {

    private String data1;
    private String data2;

   synchronized void doSomething() {
        System.out.println("stat task ...");
        try {
            Thread.sleep(3000);
            data1 = "长时间处理任务后从远处返回的 1  threadname = " + Thread.currentThread().getName();
            data2 = "长时间处理任务后从远处返回的 2  threadname = " + Thread.currentThread().getName();

            System.out.println(data1);
            System.out.println(data2);

            System.out.println("end task ...");

            /**{
             * stat task ...
             * 长时间处理任务后从远处返回的 1  threadname = thread-0
             * 长时间处理任务后从远处返回的 2  threadname = Thread-0
             * end task ...
             * stat task ...
             * 长时间处理任务后从远处返回的 1  threadname = Thread-1
             * 长时间处理任务后从远处返回的 2  threadname = Thread-1
             * end task ...
             * 耗时为 : 6
             *
             * 从运行结果上看,可以看出线程是排队进行的
             *
             * 静态类竟然可以存储变量的值  ...
             */


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

// 工具类
class CommentUtils {
    public static long beginTime1;
    public static long endTime1;
    public static long beginTime2;
    public static long endTime2;
}

// 线程1
class Thread1 extends Thread {

    private Task task;

    public Thread1(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        super.run();
        CommentUtils.beginTime1 = System.currentTimeMillis();
        task.doSomething();
        CommentUtils.endTime1 = System.currentTimeMillis();
    }
}

// 线程1
class Thread2 extends Thread {

    private Task task;

    public Thread2(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        super.run();
        CommentUtils.beginTime2 = System.currentTimeMillis();
        task.doSomething();
        CommentUtils.endTime2 = System.currentTimeMillis();
    }
}