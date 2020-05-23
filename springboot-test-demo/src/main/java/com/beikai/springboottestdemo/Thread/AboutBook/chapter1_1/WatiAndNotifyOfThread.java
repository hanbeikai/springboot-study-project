package com.beikai.springboottestdemo.Thread.AboutBook.chapter1_1;

import lombok.SneakyThrows;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/23
 * Time: 4:41 下午
 * Description: No Description
 */
public class WatiAndNotifyOfThread {

    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();

        Object o2 = new Object();

        MyThread01 myThread01 = new MyThread01(o);
        myThread01.start();

        Thread.sleep(2000);

        MyThread02 myThread02 = new MyThread02(o2);
        myThread02.start();

        /**
         * 开始等待1 -> 1590223607739
         * 开始等待2 -> 1590223609740
         * 结束等待2 -> 1590223609740
         *
         *
         * 如果线程使用同一个对象监视器的话, notify可以唤醒同一个对象监视器的wait线程,但是不能唤醒不是同一个对象监视器的wait线程
         * notifyAll 也不能唤醒不是同一个对象监视器的wait 线程
         *
         * 原因是 Synchronized 对应的对象监视器不是同一个
         */

    }
}


class MyThread01 extends Thread{
    private Object object;

    public MyThread01(Object object) {
        this.object = object;
    }

    @SneakyThrows
    @Override
    public void run() {
        synchronized (object){
            System.out.println("开始等待1 -> " + System.currentTimeMillis());
            object.wait();
            System.out.println("结束等待1 -> " + System.currentTimeMillis());
        }
    }
}


class MyThread02 extends Thread{
    private Object object;

    public MyThread02(Object object) {
        this.object = object;
    }

    @SneakyThrows
    @Override
    public void run() {
        synchronized (object){
            System.out.println("开始等待2 -> " + System.currentTimeMillis());
            object.notifyAll();
            System.out.println("结束等待2 -> " + System.currentTimeMillis());
        }
    }
}