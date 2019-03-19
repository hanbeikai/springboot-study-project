package com.beikai.springboottestdemo.threadlocktest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Test10
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/19 23:31
 * @Version 1.0
 *  通过 lock 类进行加锁
 **/
public class Test12 {

    volatile int value = 0;

    Lock lock = new ReentrantLock();

    /**
     * 调用执行方法
     */
    public void add(){
        // 获取锁
        lock.lock();
        try {
            value++;
        }finally {
            // 释放锁
            lock.unlock();
        }
    }

    /**
     * 测试方法
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Test12 Test11 = new Test12();

        List<Integer> integers = new ArrayList<>();

        for (int i = 0; i < 2; i++) {

            new Thread(() ->{
                for (int j = 0; j < 10000; j++) {
                    Test11.add();
                    System.out.println("当前线程是 : " + Thread.currentThread().getName()+"-"+Test11.value);
                    if (Test11.value == 10000){
                        integers.add(Test11.value);
                        System.out.println("--------------------------------------------------");
                    }

                }
            }).start();

        }
        Thread.sleep(1000L);
        System.out.println("结果是 : " + Test11.value);

        System.out.println(integers.get(0));
    }
}
