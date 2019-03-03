package com.beikai.springbootthread.test;

/**
 * @ClassName ExtendThreadTest
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/3 16:16
 * @Version 1.0
 *
 * 通过继承thread方式创建线程
 *
 **/
public class ExtendThreadTest extends Thread {

    // 设置线程名
    public ExtendThreadTest(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (!interrupted()){
            System.out.println(getName()+"<-- 线程执行了...");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println(getName() + "<-- 线程结束了...");
    }

    public static void main(String[] args) {
        ExtendThreadTest threadTest1 = new ExtendThreadTest("first");
        ExtendThreadTest threadTest2 = new ExtendThreadTest("second");

        threadTest1.start();
        threadTest2.start();

        threadTest1.interrupt();// 中断线程1

    }
}
