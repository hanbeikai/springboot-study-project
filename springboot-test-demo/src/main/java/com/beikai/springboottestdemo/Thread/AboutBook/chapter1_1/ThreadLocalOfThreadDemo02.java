package com.beikai.springboottestdemo.Thread.AboutBook.chapter1_1;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/27
 * Time: 8:27 上午
 * Description: 验证 threadLocal 隔离性2
 */
public class ThreadLocalOfThreadDemo02 {

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalThreadA threadLocalThreadA = new ThreadLocalThreadA();
        threadLocalThreadA.start();

        Thread.sleep(1000);

        ThreadLocalThreadB threadLocalThreadB = new ThreadLocalThreadB();
        threadLocalThreadB.start();
    }
}

class Tools{
    public static ThreadLocal<Long> threadLocal = new ThreadLocal<>();
}

class ThreadLocalThreadA extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {

            if (Tools.threadLocal.get() == null) {
                long date = System.currentTimeMillis();
                System.out.println("设置threadLocal时间为 " + date);
                Tools.threadLocal.set(date);
            }

            System.out.println("当前获取到的时间为 : " + Tools.threadLocal.get());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ThreadLocalThreadB extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {

            if (Tools.threadLocal.get() == null) {
                long date = System.currentTimeMillis();
                System.out.println("设置threadLocal时间为b " + date);
                Tools.threadLocal.set(date);
            }

            System.out.println("当前获取到的时间为b : " + Tools.threadLocal.get());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}