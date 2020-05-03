package com.beikai.springboottestdemo.Thread.AboutBook.chapter7_1;

import lombok.SneakyThrows;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/3
 * Time: 10:38 上午
 * Description: 线程池组 一级对象
 *      线程组
 *          线程
 *          线程
 *          线程
 *          ...
 *          线程
 */
public class ThreadGroupDemo01 {
    public static void main(String[] args) {
        MyThread2 mythread2 = new MyThread2();
        MyThread3 myThread3 = new MyThread3();

        // 线程组
        ThreadGroup threadGroup = new ThreadGroup("测试线程池组!");

        Thread thread = new Thread(threadGroup,mythread2);
        Thread thread1 = new Thread(threadGroup,myThread3);

        thread.setName("thread-1");
        thread.start();
        thread1.setName("thread-1");
        thread1.start();

        System.out.println("活动的线程数是 : " + threadGroup.activeCount());
        System.out.println("线程组名字 : " + threadGroup.getName());

        /**{
         * thread name  : thread-1
         * thread name2 thread-1
         * 活动的线程数是 : 2
         * 线程组名字 : 测试线程池组!
         * thread name2 thread-1
         * thread name  : thread-1
         * thread name  : thread-1
         * thread name2 thread-1
         * thread name  : thread-1
         * thread name2 thread-1
         * thread name  : thread-1
         */

    }
}

class MyThread2 extends Thread{
    @SneakyThrows
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            System.out.println("thread name  : " + Thread.currentThread().getName());
            Thread.sleep(3000);
        }
    }
}

class MyThread3 extends Thread{
    @SneakyThrows
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            System.out.println("thread name2 " + Thread.currentThread().getName());
            Thread.sleep(3000);
        }
    }
}
