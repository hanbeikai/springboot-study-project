package com.beikai.springboottestdemo.Thread.AboutBook.chapter4_1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/1
 * Time: 6:25 下午
 * Description: 测试 LockInterruptibly 方法，
 *  如果当前线程未被中断，则获取锁定，如果已经被中断，则抛出异常
 */
public class ReentrantLockAboutLockInterruptibly {

    public static void main(String[] args) throws InterruptedException {
        LockInterruptiblyService isLockService = new LockInterruptiblyService();
        Runnable runnable = new Runnable() {

            @lombok.SneakyThrows
            @Override
            public void run() {
                isLockService.serviceMethod();
            }
        };

        Thread thread = new Thread(runnable);
        thread.setName("thread-isLock");
        thread.start();
        Thread.sleep(1000);
        Thread thread2 = new Thread(runnable);
        thread2.setName("thread-isLock2");
        thread2.start();
        // 中断
        thread2.interrupt();

        System.out.println("main end ...");

    }
}

class LockInterruptiblyService{
    public ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();


    void serviceMethod() throws InterruptedException {
        //lock.lock();

        // 使用 lock.lock() 不会报错，说明 lock 不会因为线程被interrupt 而抛出异常
        // 但是使用 lock.lockInterruptibly()  回因为线程被interrupt 而抛出异常
        lock.lockInterruptibly();
        try {

            System.out.println("thread begin -> " + Thread.currentThread().getName());

            for (int i = 0; i < Integer.MAX_VALUE / 10; i++) {

                String newString = new String();
               Math.random();

            }

            System.out.println("thread end -> " + Thread.currentThread().getName());

        }finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}
