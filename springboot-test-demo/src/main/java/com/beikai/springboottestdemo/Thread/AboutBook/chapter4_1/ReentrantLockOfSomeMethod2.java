package com.beikai.springboottestdemo.Thread.AboutBook.chapter4_1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/4/29
 * Time: 10:46 下午
 * Description: lock 的一些方法
 * <p>
 * lock.hasQueuedThread(thread)  判断指定线程是否等待获取锁  true是 false 否
 * lock.hasQueuedThreads())  判断锁中是否有线程在等待获取锁
 * lock.hasWaiters(condition) 判断是否有线程正在等待与此锁定相关的condition条件 例如 await 等待的是 signal 条件 ？
 */
public class ReentrantLockOfSomeMethod2 {


    public static void main(String[] args) throws InterruptedException {
        // 测试 hasQueuedThread 和 hasQueuedThreads 方法
        //testHasQueuedThreadDemo();

        // 测试 hasWaiters 方法
        testHasWaiters();


    }

    private static void testHasWaiters() {
        LockService21 lockService2 = new LockService21();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                lockService2.waitMethod2();
            }
        };

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(runnable);

        }

        for (int i = 0; i < threads.length; i++) {
            Thread thread = threads[i];
            thread.start();
        }

        try {
            Thread.sleep(2000);

            lockService2.notifyMethod();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testHasQueuedThreadDemo() {
        LockService21 lockService21 = new LockService21();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                lockService21.waitMethod();
            }
        };

        Thread thread = new Thread(runnable);
        thread.setName("a");
        thread.start();
        Thread thread2 = new Thread(runnable);
        thread2.setName("b");
        thread2.start();

        System.out.println("线程 ： " + thread.getName() + " 是否等待获取锁 ： " + lockService21.lock.hasQueuedThread(thread));
        System.out.println("线程 ： " + thread2.getName() + " 是否等待获取锁 ： " + lockService21.lock.hasQueuedThread(thread2));
        System.out.println("所有线程是否有等待获取锁 ： " + lockService21.lock.hasQueuedThreads());
    }
}

class LockService21 {

    public ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    void waitMethod() {
        lock.lock();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void waitMethod2() {
        lock.lock();
        try {
            condition.await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void notifyMethod() {
        lock.lock();
        try {
            System.out.println("是否有线程等待 newCondition ？ " + lock.hasWaiters(condition) + " \n等待的线程数为 ： " + lock.getWaitQueueLength(condition));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}