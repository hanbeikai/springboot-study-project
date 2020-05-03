package com.beikai.springboottestdemo.Thread.AboutBook.chapter7_1;

import lombok.SneakyThrows;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/3
 * Time: 9:31 上午
 * Description: 验证线程的状态   waiting 状态   是执行了 object.wait() 方法后的状态
 *  new
 *  runnable
 *  blocke
 *  waiting
 *  terminated
 *
 */
public class StateOfThread04 {

    public static void main(String[] args) throws InterruptedException {
        MyThread mythread = new MyThread();
        mythread.start();
        Thread.sleep(1000);
        System.out.println("线程调用等待后的状态是 : " + mythread.getState());

        /**{
         * 开始等待 :
         * 线程调用等待后的状态是 : WAITING
         */

    }
}

class Lock{
    public static final byte[] lock = new byte[]{'0'};
}

class MyThread extends Thread{
    @SneakyThrows
    @Override
    public void run() {
        System.out.println("开始等待 : ");

        synchronized (Lock.lock){
            Lock.lock.wait();
        }

        System.out.println("等待结束 : ");
    }
}

