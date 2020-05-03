package com.beikai.springboottestdemo.Thread.AboutBook.chapter7_1;

import lombok.SneakyThrows;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/3
 * Time: 9:31 上午
 * Description: 验证线程的状态   blocked 状态
 *  new
 *  runnable
 *  blocke
 *  waiting
 *  terminated
 *
 */
public class StateOfThread03 {

    public static void main(String[] args) throws InterruptedException {
        Mythread3 mythread = new Mythread3();
        mythread.setName("thread-3");
        mythread.start();
        Mythread4 mythread4 = new Mythread4();
        mythread4.setName("thread-4");
        mythread4.start();
        System.out.println("main 方法中线程 3 的状态是 : " + mythread.getState());
        System.out.println("main 方法中线程 4 的状态是 : " + mythread4.getState());

        /**{
         * begin 3 state ...
         * 线程进来了 : thread-3
         * begin 4 state ...
         * main 方法中线程 3 的状态是 : RUNNABLE
         * main 方法中线程 4 的状态是 : BLOCKED          由于service 中的方法使用了同步代码块,所以当多个线程调用的时候,需要前一个线程完成,释放锁之后,此时等待的线程的状态是 blocked 状态,
         * end   3 state ...
         * 线程进来了 : thread-4
         * end   4 state ...
         */

    }
}

class MyService{

    public static synchronized void testDemo() throws InterruptedException {
        System.out.println("线程进来了 : " + Thread.currentThread().getName());
        Thread.sleep(10000);
    }
}

class Mythread3 extends Thread{

    @SneakyThrows
    @Override
    public void run() {
        super.run();
        System.out.println("begin 3 state ...");
        MyService.testDemo();
        System.out.println("end   3 state ...");

    }
}

class Mythread4 extends Thread{

    @SneakyThrows
    @Override
    public void run() {
        super.run();
        System.out.println("begin 4 state ...");
        MyService.testDemo();
        System.out.println("end   4 state ...");

    }
}

