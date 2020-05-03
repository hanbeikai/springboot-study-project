package com.beikai.springboottestdemo.Thread.AboutBook.chapter7_1;

import lombok.SneakyThrows;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/3
 * Time: 11:03 上午
 * Description: 线程组 多级关联
 *    主线程组
 *      线程
 *      线程组
 *          线程
 *          线程组
 *          ...
 *
 *    等等
 */
public class ThreadGroupDemo02 {

    public static void main(String[] args) {
        // 先创建一个主线程组,然后创建一个子线程组,再创建线程
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup group = new ThreadGroup(mainGroup,"线程组A");

        Runnable runnable = new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println("执行threadMethod!");
                Thread.sleep(1000);
                System.out.println("执行完threadMethod!");
            }
        };

        // 线程加入线程组
        Thread newThread = new Thread(group,runnable);
        newThread.setName("线程a");   // 线程必须启动才能加到线程组a中
        newThread.start();

        // 线程加入线程组
        ThreadGroup[] threadGroups = new ThreadGroup[Thread.currentThread().getThreadGroup().activeCount()];
        // 线程组加入主线程组中
        Thread.currentThread().getThreadGroup().enumerate(threadGroups);
        System.out.println("主线程组a中有" + threadGroups.length + "  线程组名字 : " + threadGroups[0].getName());

        Thread[] threads = new Thread[threadGroups[0].activeCount()];
        threadGroups[0].enumerate(threads);

        System.out.println("子线程 : " + threadGroups[0].getName());


    }

}
