package com.beikai.springboottestdemo.Thread.AboutBook.chapter1_1;

/**
 * @ClassName Test02
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/13 18:33
 * @Version 1.0
 **/
public class Test04 {
    public static void main(String[] args) {
        // 1. 方式1
        MyThread004 myThread004 = new MyThread004();
        Thread thread = new Thread(myThread004);
        thread.start();
        System.out.println("线程结束");

        // 2. 方式2
        MyThread004 myThread005 = new MyThread004();
        Thread thread2 = new Thread(myThread005,"线程2");
        thread2.start();
        System.out.println("线程结束");
    }
}

class MyThread004 implements Runnable{
    @Override
    public void run() {
        System.out.println("通过runnable 创建线程");
    }
}
