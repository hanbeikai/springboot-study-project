package com.beikai.springbootthread.test.aboutCreateThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName ExecutorsPoolThreadTest
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/3 18:03
 * @Version 1.0
 *  使用线程池创建线程
 **/
public class ExecutorsPoolThreadTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        long l = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+" 线程被执行了...");
                }
            });
        }
        long l1 = System.currentTimeMillis();
        System.out.println("多线程创建用时 : " + (l1 - l));

        // 销毁线程池
        executorService.shutdown();

    }
}
