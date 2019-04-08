package com.beikai.springboottestdemo.writtentest.Thread;

/**
 * @ClassName PrintOddAndEven
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/7 22:06
 * @Version 1.0
 * 利用线程 一次奇偶数交替打印
 **/
public class PrintOddAndEven {

    public static void main(String[] args) {
        // 声明一个打印对象
        PrintNum printNum = new PrintNum();

        // 创建两个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("打印偶数的线程 ");
                while (true) {
                    printNum.printOdd();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("打印奇数的线程");
                while (true) {
                    printNum.printEven();
                }
            }
        }).start();
    }
}
