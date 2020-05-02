package com.beikai.springboottestdemo.Thread.AboutBook.chapter1_1;

/**
 * @ClassName Test09
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/13 22:21
 * @Version 1.0
 **/
public class Test09 {
    public static void main(String[] args) throws InterruptedException {
        MyThread009 myThread009 = new MyThread009();
        myThread009.start();
        Thread.sleep(1000);
        Thread.interrupted();
    }
}
class MyThread009 extends Thread{
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 100; i++) {
            System.out.println("i=" + (i+1));
        }
    }
}
