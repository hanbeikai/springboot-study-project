package com.beikai.springbootthread.test.aboutVolatileKey;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadAboutVolatileTest01
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/24 9:42
 * @Version 1.0
 * 线程关于 volatile 关键字的使用  之  使用 volatile 关键字
 **/
public class ThreadAboutVolatileTest03 {
    // 状态标识
    private static volatile boolean flag = true;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (ThreadAboutVolatileTest03.flag) {
                    // 添加同步关键字

                    i++;
                    System.out.println("i的值是 : " + i);
                }

                System.out.println("线程结束了");
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 设置is 为false 是上面的线程结束
        ThreadAboutVolatileTest03.flag = false;

        System.out.println("已经设置为false了");
    }
}
