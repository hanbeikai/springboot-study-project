package com.beikai.springboottestdemo.Thread.AboutBook.chapter4_1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/4/28
 * Time: 8:26 上午
 * Description: 第四章第一节 condition 实现等待/通知 错误用法和解决
 *  reentratlock 使用condition 实现 单路通知 / 多路通知，比synchronized 的notify（）和 notifyAll() 更加灵活，可以实现对指定线程通知，notify（）等却不可以，
 *  notify（） 等相当于 lock对象的一个 condition 通知
 *
 *
 *  如果不使用lock ，使用 await 回报 IllegalMonitorStateException 非法的监视器异常
 *
 *  object 中的 await方法相当于 condition 中的 await方法
 *  object 中的 await(long timeout) 相当于 condition 中的await(lojng time,timeunit timeunit)
 *  object 中的nofity == condition 中的 signal 方法
 *  object 中的notifyall == condition 中的 signalAll 方法
 *
 */
public class ConditionOfLockDemo01 {

    public static void main(String[] args) throws InterruptedException {
        // 实现单个等待通知
        MyServiceCondition01 myServiceCondition01 = new MyServiceCondition01();
        Mythread03 mythread03 = new Mythread03(myServiceCondition01);
        mythread03.start();
        Thread.sleep(1000);
        myServiceCondition01.signal();
    }

}

class Mythread03 extends Thread{

    private MyServiceCondition01 myServiceCondition01;

    Mythread03(MyServiceCondition01 myServiceCondition01){
        super();
        this.myServiceCondition01 = myServiceCondition01;
    }

    @Override
    public void run() {
        myServiceCondition01.await();
    }
}

class MyServiceCondition01{
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    void await(){
        lock.lock();
        try {
            System.out.println("start await -> time is " + System.currentTimeMillis());
            condition.await();
            System.out.println("end await -> time is " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
            System.out.println("lock await is unlock ...");
        }
    }

    void signal(){
        lock.lock();
        try {
            System.out.println("start signal -> time is " + System.currentTimeMillis());
            condition.signal();
            System.out.println("end signal -> time is " + System.currentTimeMillis());
        }finally {
            lock.unlock();
            System.out.println("lock signal is unlock ...");
        }
    }

}
