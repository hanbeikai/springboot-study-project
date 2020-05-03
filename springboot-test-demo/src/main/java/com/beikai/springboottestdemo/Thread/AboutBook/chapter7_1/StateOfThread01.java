package com.beikai.springboottestdemo.Thread.AboutBook.chapter7_1;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/3
 * Time: 9:31 上午
 * Description: 验证线程的状态
 *  new
 *  runnable
 *  blocke
 *  waiting
 *  time waiting
 *  terminated
 *
 *      注意，此时构造方法中的线程的状态是 main线程的状态
 */
public class StateOfThread01 {

    public static void main(String[] args) throws InterruptedException {
        Mythread mythread = new Mythread();
        System.out.println("main方法中线程的状态 : " + mythread.getState());

        Thread.sleep(1000);
        mythread.start();
        Thread.sleep(1000);

        System.out.println("main方法中线程的状态2 : " + mythread.getState());

        /**
         * 构造方法中线程的状态是 : RUNNABLE
         * main方法中线程的状态 : NEW
         * 执行方法中线程的状态是 : RUNNABLE
         * main方法中线程的状态2 : TERMINATED
         */
    }
}


class Mythread extends Thread{

    public Mythread() {
        System.out.println("构造方法中线程的状态是 : " + Thread.currentThread().getState());
    }

    @Override
    public void run() {
        super.run();
        System.out.println("执行方法中线程的状态是 : " + Thread.currentThread().getState());
    }
}
