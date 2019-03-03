package com.beikai.springbootthread.test;

/**
 * @ClassName ImplementsRunnableTest
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/3 16:25
 * @Version 1.0
 *
 *  通过实现runnable方式创建线程
 **/
public class ImplementsRunnableTest implements Runnable{

    @Override
    public void run() {
        int num = 0;
        while (num < 10){
            System.out.println("线程执行了...");
            num++;
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new ImplementsRunnableTest());

        thread.start();
    }
}
