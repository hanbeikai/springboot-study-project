package com.beikai.springboottestdemo.Thread.AboutBook.chapter4_1;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/4/29
 * Time: 10:21 下午
 * Description: 第四章第一节 公平锁与非公平锁
 *
 *  公平锁  ：  线程获取锁的顺序是按照加锁的顺序，先加锁的先获取锁
 *  非公平锁 ： 不是按照加锁的顺序获取锁，有可能先加锁的后获取到锁， 所以为非公平锁
 *
 * 公平锁情况 ：
 * ⭐️ 线程 Thread-2运行了！
 * ⭐️ 线程 Thread-4运行了！
 * 线程 ： Thread-2 获取到锁！
 * ⭐️ 线程 Thread-1运行了！
 * 线程 ： Thread-4 获取到锁！
 * ⭐️ 线程 Thread-5运行了！
 * ⭐️ 线程 Thread-3运行了！
 * ⭐️ 线程 Thread-6运行了！
 * ⭐️ 线程 Thread-0运行了！
 * ⭐️ 线程 Thread-7运行了！
 * ⭐️ 线程 Thread-8运行了！
 * ⭐️ 线程 Thread-9运行了！
 * 线程 ： Thread-1 获取到锁！
 * 线程 ： Thread-5 获取到锁！
 * 线程 ： Thread-3 获取到锁！
 * 线程 ： Thread-6 获取到锁！
 * 线程 ： Thread-0 获取到锁！
 * 线程 ： Thread-7 获取到锁！
 * 线程 ： Thread-8 获取到锁！
 * 线程 ： Thread-9 获取到锁！
 *
 * 非公平锁情况
 * ⭐️ 线程 Thread-2运行了！
 * ⭐️ 线程 Thread-4运行了！
 * ⭐️ 线程 Thread-5运行了！
 * ⭐️ 线程 Thread-3运行了！
 * ⭐️ 线程 Thread-1运行了！
 * ⭐️ 线程 Thread-0运行了！
 * ⭐️ 线程 Thread-6运行了！
 * 线程 ： Thread-2 获取到锁！
 * ⭐️ 线程 Thread-8运行了！
 * ⭐️ 线程 Thread-9运行了！
 * ⭐️ 线程 Thread-7运行了！
 * 线程 ： Thread-8 获取到锁！
 * 线程 ： Thread-4 获取到锁！
 * 线程 ： Thread-5 获取到锁！
 * 线程 ： Thread-3 获取到锁！
 * 线程 ： Thread-1 获取到锁！
 * 线程 ： Thread-0 获取到锁！
 * 线程 ： Thread-6 获取到锁！
 * 线程 ： Thread-9 获取到锁！
 * 线程 ： Thread-7 获取到锁！
 *
 */
public class ConditionOfUnFairOrNotDemo01 {

    public static void main(String[] args) {
        // 公平锁
        //FairService fairService = new FairService(true);
        // 非公平锁
        FairService fairService = new FairService(false);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("⭐️ 线程 " + Thread.currentThread().getName() + "运行了！");
                fairService.serviceMethod();
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

    }
}


class FairService{
    private ReentrantLock lock;

    FairService(Boolean isFair){
        super();
        this.lock = new ReentrantLock(isFair);
    }

    void serviceMethod(){
        lock.lock();

        try {
            System.out.println("线程 ： " + Thread.currentThread().getName() +" 获取到锁！");
        }finally {
            lock.unlock();
        }
    }
}