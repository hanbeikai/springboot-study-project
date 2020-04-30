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
 * reentratlock 使用condition 实现 单路通知 / 多路通知，比synchronized 的notify（）和 notifyAll() 更加灵活，可以实现对指定线程通知，notify（）等却不可以，
 * notify（） 等相当于 lock对象的一个 condition 通知
 * <p>
 * <p>
 * 如果不使用lock ，使用 await 回报 IllegalMonitorStateException 非法的监视器异常
 * <p>
 * object 中的 await方法相当于 condition 中的 await方法
 * object 中的 await(long timeout) 相当于 condition 中的await(lojng time,timeunit timeunit)
 * object 中的nofity == condition 中的 signal 方法
 * object 中的notifyall == condition 中的 signalAll 方法
 */
public class ConditionOfLockDemo05 {

    public static void main(String[] args) throws InterruptedException {
        // 使用condition 和 reentrantlock 实现 生产者和消费者 交替打印(多对多)
        // 但是可能会出现 重复打印的情况，原因时 多个线程中，可能会出现当ishavevlaue为true同时等待的情况
        // 这种情况是线程的假死情况 ，解决办法是 唤醒所有的线程
        MyServiceCondition05 myServiceCondition01 = new MyServiceCondition05();

        Mythread011[] mythread011s = new Mythread011[10];
        Mythread012[] mythread012s = new Mythread012[10];

        System.out.println("-------------------开始-----------------------");
        for (int i = 0; i < 10; i++) {
            mythread011s[i] = new Mythread011(myServiceCondition01);
            mythread011s[i].setName("  set : " + i);
            mythread011s[i].start();

            mythread012s[i] = new Mythread012(myServiceCondition01);
            mythread012s[i].setName("  get : " + i);
            mythread012s[i].start();
        }


    }

}

class Mythread011 extends Thread {
    private MyServiceCondition05 myServiceCondition01;

    Mythread011(MyServiceCondition05 myServiceCondition01) {
        super();
        this.myServiceCondition01 = myServiceCondition01;
    }

    @Override
    public void run() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            myServiceCondition01.await(i);
        }
    }
}

class Mythread012 extends Thread {
    private MyServiceCondition05 myServiceCondition01;

    Mythread012(MyServiceCondition05 myServiceCondition01) {
        super();
        this.myServiceCondition01 = myServiceCondition01;
    }

    @Override
    public void run() {

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            myServiceCondition01.signal(i);
        }
    }
}

class MyServiceCondition05 {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private Boolean isHaveVlaue = false;

    void await(int i) {
        lock.lock();
        try {
            // ishavevlaue 为true 等待
            while (isHaveVlaue) {
                System.out.println("出现了连续的情况☆☆" + Thread.currentThread().getName());
                condition.await();
            }
            // 为false ，设置为true
            isHaveVlaue = true;
            //System.out.println("set  ishavevlaue 的 值为 ： " + isHaveVlaue);
            // 唤醒 ishavevalue为 false时 休眠的线程
            /**
             * 功能描述:  解决出现假死的情况 是使用通知所有的
             * 出现了连续的情况⭐️⭐Thread-1
             * 出现了连续的情况⭐️⭐Thread-3
             * ⭐️⭐Thread-2
             * 出现了连续的情况⭐️Thread-2
             * 出现了连续的情况⭐️Thread-4
             */
            System.out.println("打印 ☆");
            //condition.signal();
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " 释放了锁 ！（set）【" + isHaveVlaue + " -> " + i + " 】");
        }
    }

    void signal(int i) {
        lock.lock();
        try {
            // 为false时， 等待
            while (!isHaveVlaue) {
                System.out.println("出现了连续的情况★★" + Thread.currentThread().getName());
                condition.await();
            }
            // 为true时，谁知为false
            isHaveVlaue = false;
            //System.out.println("get  ishavevlaue 的 值为 ： " + isHaveVlaue);
            // 唤醒 ishavevlaue为 true时 休眠的线程
            /**
             * 功能描述:  解决出现假死的情况 是使用通知所有的
             * 出现了连续的情况⭐️⭐Thread-1
             * 出现了连续的情况⭐️⭐Thread-3
             * ⭐️⭐Thread-2
             * 出现了连续的情况⭐️Thread-2
             * 出现了连续的情况⭐️Thread-4
             */
            System.out.println("打印 ★");
            //condition.signal();
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " 释放了锁 ！（get）【" + isHaveVlaue + " -> " + i + " 】");
        }
    }

}
