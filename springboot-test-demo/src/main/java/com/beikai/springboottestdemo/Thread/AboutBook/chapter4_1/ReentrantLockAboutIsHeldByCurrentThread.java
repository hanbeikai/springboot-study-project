package com.beikai.springboottestdemo.Thread.AboutBook.chapter4_1;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/1
 * Time: 7:56 上午
 * Description:
 *  lock.isHeldByCurrentThread()  查询当前线程是否保持此锁定
 */
public class ReentrantLockAboutIsHeldByCurrentThread {

    public static void main(String[] args) {
        final IsHeldByCurrentThreadService isFairService = new IsHeldByCurrentThreadService();

        // 设置lock为true
        doHandle(false,"isFare = true",isFairService);
        // 设置lock为false
        doHandle(false,"isFare = false",isFairService);

        doHandle(false,"isFare = false2",isFairService);
    }

    private static void doHandle(Boolean isFair,String name,IsHeldByCurrentThreadService isHeldByCurrentThreadService) {
        //final IsHeldByCurrentThreadService isHeldByCurrentThreadService = new IsHeldByCurrentThreadService();
        isHeldByCurrentThreadService.setLock(isFair);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                isHeldByCurrentThreadService.serviceMethod();
            }
        };

        Thread thread = new Thread(runnable);
        thread.setName(name);
        thread.start();
    }

}


class IsHeldByCurrentThreadService{
    private ReentrantLock lock;

    public void setLock(Boolean lock) {
        this.lock = new ReentrantLock(lock);
    }

    void serviceMethod(){
        System.out.println("线程前 " + Thread.currentThread().getName() + " 是否保持锁定 ： " + lock.isHeldByCurrentThread());
        lock.lock();
        try {
            System.out.println("线程后 " + Thread.currentThread().getName() + " 是否保持锁定 ： " + lock.isHeldByCurrentThread());

            //Thread.sleep(1000);

        }  finally {

            // 为啥这没有释放锁，但是其他线程仍然可以获取到锁 ？
            /**
             *  线程前 isFare = true 是否保持锁定 ： false
             * 线程前 isFare = false 是否保持锁定 ： false
             * 线程前 isFare = false 是否保持锁定 ： false
             * 线程后 isFare = false 是否保持锁定 ： true
             * 线程后 isFare = true 是否保持锁定 ： true
             * 线程后 isFare = false 是否保持锁定 ： true
             * 线程isFare = false 释放锁
             * 线程isFare = false 释放锁
             * 当前线程 isFare = false 锁状态是 ： true
             * 线程isFare = true 释放锁
             * 当前线程 isFare = false 锁状态是 ： true
             * 当前线程 isFare = true 锁状态是 ： true
             *
             * 由于上面的dohandle方法内创建的lock，导致三次调用生成了三个锁，每个锁对应一个线程，所以没有锁住
             *
             * 线程前 isFare = false 是否保持锁定 ： false
             * 线程前 isFare = false2 是否保持锁定 ： false
             * 线程前 isFare = true 是否保持锁定 ： false
             * 线程后 isFare = false 是否保持锁定 ： true
             * 线程isFare = false 释放锁
             * 当前线程 isFare = false 锁状态是 ： true
             *
             * 实际情况是可以锁住的 如果不释放锁
             *
             *
             */

            //lock.unlock();
            System.out.println("线程" + Thread.currentThread().getName() + " 释放锁");
            System.out.println("当前线程 " + Thread.currentThread().getName() + " 锁状态是 ： " + lock.isLocked());
        }
    }
}