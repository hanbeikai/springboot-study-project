package com.beikai.springbootthread.test.threadlocktest;

import java.util.ArrayList;
import java.util.List;

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

    public void add(){
        id++;
    }

    /**
     * 测试方法
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Test08 test08 = new Test08();

        List<Integer> integers = new ArrayList<>();

        for (int i = 0; i < 2; i++) {

            new Thread(() ->{
                for (int j = 0; j < 10000; j++) {
                    test08.add();
                    System.out.println("当前线程是 : " + Thread.currentThread().getName()+"-"+test08.id);
                    if (test08.id == 10000){
                        integers.add(test08.id);
                        System.out.println("--------------------------------------------------");
                    }
                    
                }
            }).start();
            
        }
        Thread.sleep(1000L);
        System.out.println("结果是 : " + test08.id);

        System.out.println(integers.get(0));
    }
}
