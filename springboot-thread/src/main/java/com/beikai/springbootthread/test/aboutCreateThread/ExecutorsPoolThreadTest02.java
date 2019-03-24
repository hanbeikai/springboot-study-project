package com.beikai.springbootthread.test.aboutCreateThread;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * @ClassName ExecutorsPoolThreadTest02
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/23 10:29
 * @Version 1.0
 *
 * 多个线程池
 *
 **/
public class ExecutorsPoolThreadTest02 {

    public static void main(String[] args) {
        for (int j = 0; j < 10; j++) {
            ExecutorService executorService = newFixedThreadPool(100);
            for (int i = 0; i < 1000; i++) {
                final int index = i;
                try {
                    executorService.execute(new Runnable() {
                                                @Override
                                                public void run() {
                                                    try {
                                                        System.out.println("当前线程是 : " + Thread.currentThread().getName() + "-" + index);
                                                        Thread.sleep(1000);
                                                    } catch (InterruptedException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }

                    );

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            executorService.shutdown();
        }
    }
}
