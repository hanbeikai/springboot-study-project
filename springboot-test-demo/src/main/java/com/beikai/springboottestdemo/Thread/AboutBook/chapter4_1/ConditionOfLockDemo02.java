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
public class ConditionOfLockDemo02 {

    public static void main(String[] args) throws InterruptedException {
        // 实现多个线程通知
        MyServiceCondition02 myServiceCondition01 = new MyServiceCondition02();
        Mythread05 mythread03 = new Mythread05(myServiceCondition01);
        mythread03.setName("a");
        mythread03.start();
        Mythread06 mythread06 = new Mythread06(myServiceCondition01);
        mythread06.setName("b");
        mythread06.start();
        Thread.sleep(1000);
        myServiceCondition01.signalAll();

    }

}

class Mythread05 extends Thread{

    private MyServiceCondition02 myServiceCondition01;

    Mythread05(MyServiceCondition02 myServiceCondition01){
        super();
        this.myServiceCondition01 = myServiceCondition01;
    }

    @Override
    public void run() {
        myServiceCondition01.awaitA();
    }
}

class Mythread06 extends Thread{

    private MyServiceCondition02 myServiceCondition01;

    Mythread06(MyServiceCondition02 myServiceCondition01){
        super();
        this.myServiceCondition01 = myServiceCondition01;
    }

    @Override
    public void run() {
        myServiceCondition01.awaitB();
    }
}

class MyServiceCondition02{
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    void awaitA(){
        lock.lock();
        try {
            System.out.println("start await a -> time is " + System.currentTimeMillis()  + " -> thread name is " + Thread.currentThread().getName());
            condition.await();
            System.out.println("end await a -> time is " + System.currentTimeMillis()  + " -> thread name is " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
            System.out.println("lock await a is unlock ...");
        }
    }
    void awaitB(){
        lock.lock();
        try {
            System.out.println("start await b -> time is " + System.currentTimeMillis()  + " -> thread name is " + Thread.currentThread().getName());
            condition.await();
            System.out.println("end await b -> time is " + System.currentTimeMillis()  + " -> thread name is " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
            System.out.println("lock await b is unlock ...");
        }
    }

    void signal(){
        lock.lock();
        try {
            System.out.println("start signal -> time is " + System.currentTimeMillis()  + " -> thread name is " + Thread.currentThread().getName());
            condition.signal();
            System.out.println("end signal -> time is " + System.currentTimeMillis()  + " -> thread name is " + Thread.currentThread().getName());
        }finally {
            lock.unlock();
            System.out.println("lock signal is unlock ...");
        }
    }

    void signalAll(){
        lock.lock();
        try {
            System.out.println("start signal all -> time is " + System.currentTimeMillis()  + " -> thread name is " + Thread.currentThread().getName());
            condition.signalAll();
            System.out.println("end signal all -> time is " + System.currentTimeMillis()  + " -> thread name is " + Thread.currentThread().getName());
        }finally {
            lock.unlock();
            System.out.println("lock signal all is unlock ...");
        }
    }

}
