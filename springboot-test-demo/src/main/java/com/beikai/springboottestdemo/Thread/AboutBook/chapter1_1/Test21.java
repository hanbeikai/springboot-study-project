package com.beikai.springboottestdemo.Thread.AboutBook.chapter1_1;

import java.util.Random;

/**
 * @ClassName Test21
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/14 12:15
 * @Version 1.0
 **/
public class Test21 {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            MyThread21_1 myThread21_1 = new MyThread21_1();
            myThread21_1.setPriority(10);
            myThread21_1.start();

            MyThread21_2 myThread21_2 = new MyThread21_2();
            myThread21_2.setPriority(1);
            myThread21_2.start();
            
        }
    }
}
class MyThread21_1 extends Thread{
    @Override
    public void run() {
        super.run();
        long l = System.currentTimeMillis();
        long addresult = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5000; j++) {
                Random random = new Random();
                random.nextInt();
                addresult = addresult + 1;
            }
        }
        System.out.println("★ ★ ★ ★ ★ 当前线程用时 : " + (System.currentTimeMillis() - l));
    }
}
class MyThread21_2 extends Thread{
    @Override
    public void run() {
        super.run();
        long l = System.currentTimeMillis();
        long addresult = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5000; j++) {
                Random random = new Random();
                random.nextInt();
                addresult = addresult + 1;
            }
        }
        System.out.println("☆ ☆ ☆ ☆ ☆ 当前线程用时 : " + (System.currentTimeMillis() - l));
    }
}
