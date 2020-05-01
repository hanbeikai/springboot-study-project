package com.beikai.springboottestdemo.Thread.AboutBook.chapter4_1;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/1
 * Time: 6:25 下午
 * Description: 测试 isLock 方法，查询此锁定是否又任意线程保持
 */
public class ReentrantLockAboutIsLock {

    public static void main(String[] args) {
        IsLockService isLockService = new IsLockService(true);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                isLockService.serviceMethod();
            }
        };

        Thread thread = new Thread(runnable);
        thread.setName("thread-isLock");
        thread.start();


        /**
         *  当前线程 thread-isLock 是否又锁定 ： false
         *  当前线程 thread-isLock 是否又锁定 ： true
         *  当前线程 thread-isLock 是否又锁定 ： false
         *
         *
         *  在线程上锁之前，当前线程未被锁定
         *  调用lock.lock之后，当前线程上锁
         *  在调用lock.unlock() 之后，当前线程未被锁定
         */
    }
}

class IsLockService{
    private ReentrantLock lock;

    IsLockService(Boolean isFair){
        super();
        this.lock = new ReentrantLock(isFair);
    }

    void serviceMethod(){
        System.out.println("当前线程 " + Thread.currentThread().getName() + " 是否又锁定 ： " + lock.isLocked());
        lock.lock();
        try {
            System.out.println("当前线程 " + Thread.currentThread().getName() + " 是否又锁定 ： " + lock.isLocked());
        }finally {
            lock.unlock();
            System.out.println("当前线程 " + Thread.currentThread().getName() + " 是否又锁定 ： " + lock.isLocked());
        }
    }
}
