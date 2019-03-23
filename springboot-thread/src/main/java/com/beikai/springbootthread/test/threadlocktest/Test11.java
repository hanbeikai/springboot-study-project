package com.beikai.springbootthread.test.threadlocktest;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Test10
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/19 23:31
 * @Version 1.0
 *  通过 加 锁的形式 保证线程安全
 **/
public class Test11 {

    volatile int value = 0;

    /**
     * 调用执行方法
     */
    public void add(){
        synchronized (this){
            value++;
        }
    }

    /**
     * 测试方法
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Test11 Test11 = new Test11();

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
