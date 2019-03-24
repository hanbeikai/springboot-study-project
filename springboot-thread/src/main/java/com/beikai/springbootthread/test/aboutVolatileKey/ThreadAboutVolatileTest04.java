package com.beikai.springbootthread.test.aboutVolatileKey;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName ThreadAboutVolatileTest01
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/24 9:42
 * @Version 1.0
 * 线程关于 volatile 关键字的使用  之  使用 volatile 关键字 2
 **/
public class ThreadAboutVolatileTest04 {
    // 状态标识
    public static volatile int count = 0;

    public static void increment(){
        count++;
    }

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(20);

        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        ThreadAboutVolatileTest04.increment();
                    }
                    // 计数减一
                    countDownLatch.countDown();
                }
            }).start();
        }

        try {
            // 计数等待
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("计数总数是 : " + ThreadAboutVolatileTest04.count);
    }
}
