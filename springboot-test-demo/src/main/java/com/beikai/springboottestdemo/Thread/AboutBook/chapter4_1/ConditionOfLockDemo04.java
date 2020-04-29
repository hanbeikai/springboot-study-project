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
public class ConditionOfLockDemo04 {

    public static void main(String[] args) throws InterruptedException {
        // 使用condition 和 reentrantlock 实现 生产者和消费者 交替打印
        MyServiceCondition04 myServiceCondition01 = new MyServiceCondition04();
        Mythread09 mythread03 = new Mythread09(myServiceCondition01);
        mythread03.setName("a");
        mythread03.start();

        Mythread010 mythread06 = new Mythread010(myServiceCondition01);
        mythread06.setName("b");
        mythread06.start();


    }

}

class Mythread09 extends Thread {
    private MyServiceCondition04 myServiceCondition01;

    Mythread09(MyServiceCondition04 myServiceCondition01) {
        super();
        this.myServiceCondition01 = myServiceCondition01;
    }

    @Override
    public void run() {

        while (true) {

            myServiceCondition01.await();
        }
    }
}

class Mythread010 extends Thread {
    private MyServiceCondition04 myServiceCondition01;

    Mythread010(MyServiceCondition04 myServiceCondition01) {
        super();
        this.myServiceCondition01 = myServiceCondition01;
    }

    @Override
    public void run() {
        while (true) {

            myServiceCondition01.signal();
        }

    }
}

class MyServiceCondition04 {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private Boolean isHaveVlaue = false;

    void await() {
        lock.lock();
        try {
            // ishavevlaue 为true 等待
            if (isHaveVlaue) {
                condition.await();
            }
            // 为false ，设置为true
            isHaveVlaue = true;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("⭐️⭐");
            // 唤醒 ishavevalue为 false时 休眠的线程
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("lock await a is unlock ...");
        }
    }

    void signal() {
        lock.lock();
        try {
            // 为false时， 等待
            if (!isHaveVlaue) {
                condition.await();
            }
            // 为true时，谁知为false
            isHaveVlaue = false;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("⭐️");
            // 唤醒 ishavevlaue为 true时 休眠的线程
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("lock signal is unlock ...");
        }
    }

}
