package com.beikai.springboottestdemo.Thread.AboutBook.chapter1_1;

/**
 * @ClassName Test19
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/14 11:52
 * @Version 1.0
 **/
public class Test19 {
    public static void main(String[] args) {
        MyThread19 myThread19 = new MyThread19();
        myThread19.start();
    }
}
class MyThread19 extends Thread{
    @Override
    public void run() {
        super.run();
        long l = System.currentTimeMillis();
        int count = 0;
        for (int i = 0; i < 50000000; i++) {
            Thread.yield();
            count = count + (i + 1);
        }
        System.out.println("费时 : " + (System.currentTimeMillis() - l));
    }
}
