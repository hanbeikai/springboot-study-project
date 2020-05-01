package com.beikai.springboottestdemo.Thread.AboutBook.chapter4_1;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/1
 * Time: 7:56 上午
 * Description: 测试 isFair 方法，方法判断是不是公平锁
 *  默认情况下，reentrantLock 使用的是非公平锁
 */
public class ReentrantLockAboutIsFair {

    public static void main(String[] args) {
        // 设置lock为true
        doHandle(true,"isFare = true");
        // 设置lock为false
        doHandle(false,"isFare = false");

        /**
         * 当前 isFare = true 是否拥有公平锁 ： true
         * 当前 isFare = false 是否拥有公平锁 ： false
         */
    }

    private static void doHandle(Boolean isFair,String name) {
        final IsFairService isFairService = new IsFairService(isFair);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                isFairService.serviceMethod();
            }
        };

        Thread thread = new Thread(runnable);
        thread.setName(name);
        thread.start();
    }

}


class IsFairService{
    private ReentrantLock lock;

    IsFairService(boolean isFair){
        super();
        this.lock = new ReentrantLock(isFair);
    }


    void serviceMethod(){
        lock.lock();
        try {
            System.out.println("当前 "+Thread.currentThread().getName()+" 是否拥有公平锁 ： " + lock.isFair());
        } finally {
            lock.unlock();
        }
    }
}