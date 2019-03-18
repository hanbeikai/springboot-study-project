package com.beikai.springboottestdemo.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName Test08
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/19 7:34
 * @Version 1.0
 *volatile 关键字的线程
 *
 **/
public class Test08 {
    /**
     * 线程不安全
     */
    volatile int id = 0;

    /**
     * 线程安全
      */
    AtomicInteger atomicInteger = new AtomicInteger(0);

    public void add2(){
        atomicInteger.incrementAndGet();// 自带的自增方法
    }

    public void add(){
        id++;
    }

    public static void main(String[] args) throws InterruptedException {
        Test08 test08 = new Test08();

        List<Integer> integers = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            new Thread(() ->{
                for (int j = 0; j < 10000; j++) {
                    test08.add2();

                    System.out.println("当前线程是 : " + Thread.currentThread().getName()+"-"+test08.atomicInteger);
                    if (test08.atomicInteger.get() == 10000){
                        integers.add(test08.atomicInteger.get());
                        System.out.println("--------------------------------------------------");
                    }
                    
                }
            }).start();
            
        }
        Thread.sleep(1000L);
        System.out.println("结果是 : " + test08.atomicInteger.get());

        System.out.println(integers.get(0));
    }
}
