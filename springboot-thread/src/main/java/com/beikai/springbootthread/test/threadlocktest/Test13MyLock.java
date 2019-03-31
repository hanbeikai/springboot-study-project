package com.beikai.springbootthread.test.threadlocktest;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @ClassName Test13MyLock
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/20 7:35
 * @Version 1.0
 *  自定义一把锁
 **/
public class Test13MyLock implements Lock {

    /**
     * 声明一个锁对象
     * AtomicReference 可以 保证一个对象的原子性
     */
    AtomicReference<Thread> currentThread = new AtomicReference<>();

    /**
     * 集合 -- 用于存储正在等待额线程
     */
    public LinkedBlockingQueue<Thread> writeThread = new LinkedBlockingQueue<>();

    /**
     * 获取锁的操作
     */
    @Override
    public void lock() {
        // cas 第一个参数是 当前值, 第二个参数是 要改变的值
        while (!currentThread.compareAndSet(null,Thread.currentThread())){
            // 没有拿到锁,需要等待,其他线程释放锁
            // 等待的线程加入集合中
            writeThread.add(Thread.currentThread());
            // 通知  wait 和 notify ,但是这两个都不能用,因为 使用这两个的前提是使用同步关键字 synchronized  所以 可以使用 part unpark

            // park 的作用是 让一个线程等待   线程到这之后, 会停在这 一直等到被唤醒
            LockSupport.park();

            // 唤醒之后, 把当前线程从集合中移除
            writeThread.remove(Thread.currentThread());

         }

        // 拿到锁之后的操作
    }

    /**
     * 释放锁的操作
     */
    @Override
    public void unlock() {
        if (currentThread.compareAndSet(Thread.currentThread(),null)){
            // 释放锁之后,告知其他线程, 你们可以继续抢夺锁
            // 遍历存放所有线程的集合
            for (Object o : writeThread.toArray()) {
                Thread next = (Thread) o;

                // 通过其他线程 开始抢夺锁
                LockSupport.unpark(next);
            }
        }

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
