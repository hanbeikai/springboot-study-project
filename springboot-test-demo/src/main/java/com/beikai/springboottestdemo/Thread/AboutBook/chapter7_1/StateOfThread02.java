package com.beikai.springboottestdemo.Thread.AboutBook.chapter7_1;

import lombok.SneakyThrows;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/3
 * Time: 9:31 上午
 * Description: 验证线程的状态   time waiting
 *  new
 *  runnable
 *  blocke
 *  waiting
 *  terminated
 *
 *      注意 ： 线程sleep() 后线程处于 timed waiting 状态
 */
public class StateOfThread02 {

    public static void main(String[] args) throws InterruptedException {
        Mythread2 mythread = new Mythread2();

        mythread.start();
        Thread.sleep(1000);
        System.out.println("main 方法中线程的状态是 : " + mythread.getState());

        /**{
         * 构造方法中线程的状态是 : RUNNABLE
         * begin state ...
         * main 方法中线程的状态是 : TIMED_WAITING   主线程在等待阻塞线程完成
         * begin   end ...
         */

    }
}


class Mythread2 extends Thread{

    public Mythread2() {
        System.out.println("构造方法中线程的状态是 : " + Thread.currentThread().getState());
    }

    @SneakyThrows
    @Override
    public void run() {
        super.run();
        System.out.println("begin state ...");
        Thread.sleep(10000);
        System.out.println("begin   end ...");

    }
}
