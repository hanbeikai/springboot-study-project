package com.beikai.springboottestdemo.Thread.AboutBook.chapter4_1;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/1
 * Time: 6:58 下午
 * Description:
 *      tryLock() 方法，仅在调用时锁未被另一个线程占用的情况下，才能获取该锁
 *
 *      tryLock(long time，timeUnit unit) 方法，锁在给定的时间内未被另一个线程占用的情况下，才能获取该锁
 */
public class ReentrantLockAboutTryLock {

    public static void main(String[] args) throws InterruptedException {
        TryLockService tryLockService = new TryLockService();
        Runnable runnable = new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                tryLockService.tryLockService();
            }
        };

        Thread thread = new Thread(runnable);
        thread.setName("thread-a");
        thread.start();
        Thread.sleep(1000);

        Thread thread1 = new Thread(runnable);
        thread1.setName("thread-b");
        thread1.start();


        /**
         *  使用 lock.tryLock() 方法时
         *
         *  线程 thread-a 获取到锁
         *  线程 thread-b 未获取到锁
         *
         *  线程a和线程b由于启动时间接近，当线程a获取到锁后，线程b尝试获取锁，但是此时锁被线程a占用，导致线程b不能获取到锁
         *  （如果在中间添加一个thread.sleep()）,则会推迟线程b的启动时间，当线程b启动后，由于线程a已经释放锁，所以线程b可以得到锁
         *
         *  tryLock(long time，timeUnit unit) 方法时
         *  1 。如果线程 a 在没有释放锁，会导致线程2一直获取不到锁
         *
         *  2 。如果线程 a 在线程b设置的等待时间内没有释放锁，线程b获取不到锁
         *          前提条件是 ： 线程a与线程b启动的时间间隔+线程a执行时间 > 线程b获取锁的指定时间
         *
         */
    }
}


class TryLockService{
    private ReentrantLock lock = new ReentrantLock();

    void tryLockService() throws InterruptedException {
        // if (lock.tryLock()) {
        if (lock.tryLock(2,TimeUnit.SECONDS)) {
            try {
                System.out.println("线程 " + Thread.currentThread().getName() + " 获取到锁");
                Thread.sleep(4000);

            } finally {
                // 判断当前线程在获取到锁的情况下，释放锁
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        }else {
            System.out.println("线程 " + Thread.currentThread().getName() + " 未获取到锁");
        }
    }
}