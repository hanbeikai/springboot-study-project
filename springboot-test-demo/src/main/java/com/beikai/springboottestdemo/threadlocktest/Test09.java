package com.beikai.springboottestdemo.threadlocktest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName Test09
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/19 23:19
 * @Version 1.0
 *  测试  AtomicInteger
 **/
public class Test09 {

    /**
     * AtomicInteger 关键字 通过 cas 机制进行硬件内存底层操作, 每一次操作需要输入两个数,当前数 和 要变成的数,
     * 当前数与内存中的值进行比较, 如果相同, 变成要变成的数,如果不同,返回错误
     */
    AtomicInteger atomicInteger = new AtomicInteger(0);

    public void add(){
        // 自带的自增的方法
        atomicInteger.incrementAndGet();
    }

    /**
     *  创建方法调用
      */
    public static void main(String[] args) throws InterruptedException {
        Test09 test09 = new Test09();

        List<Integer> integers = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    test09.add();
                    System.out.println("当前线程是 : " + Thread.currentThread().getName() + "-" + test09.atomicInteger.get());
                    if (test09.atomicInteger.get() == 1000){
                        integers.add(test09.atomicInteger.intValue());
                    }
                }

            }).start();
        }
        Thread.sleep(1000L);

        System.out.println("线程结束 ! 结果是 : " + test09.atomicInteger.get());

        System.out.println("收集的结果是 : " + integers.get(0));
    }
}
