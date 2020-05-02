package com.beikai.springboottestdemo.Thread.AboutBook.chapter1_1;

/**
 * @ClassName Test20
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/14 12:00
 * @Version 1.0
 **/
public class Test20 {
    public static void main(String[] args) {
        System.out.println("main 优先级 start : " + Thread.currentThread().getPriority());
        Thread.currentThread().setPriority(6);
        System.out.println("main 优先级 end : " + Thread.currentThread().getPriority());
        MyThread20 myThread20 = new MyThread20();
        myThread20.start();
    }
}
class MyThread20 extends Thread{
    @Override
    public void run() {
        super.run();
        System.out.println("MyThread20 线程的优先级是 : " + this.getPriority());
        MyThread20_1 myThread20_1 = new MyThread20_1();
        myThread20_1.start();
    }
}
class MyThread20_1 extends Thread{
    @Override
    public void run() {
        super.run();
        System.out.println("MyThread20_1 线程的优先级是 : " + this.getPriority());
    }
}
