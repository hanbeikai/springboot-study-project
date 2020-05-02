package com.beikai.springboottestdemo.Thread.AboutBook.chapter1_1;

/**
 * @ClassName Test02
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/13 18:33
 * @Version 1.0
 **/
public class Test02 {
    public static void main(String[] args) {
        MyThread001 myThread001 = new MyThread001();
        // 创建线程的方式有两个
        // 1. 调用 start()方法
        myThread001.start();

        // 2. 由于thread也是实现的runnable,所以可以使用构造器注入的方式
        /*Thread thread = new Thread(myThread001);
        thread.start();*/

        System.out.println("线程结束");

    }
}
class MyThread001 extends Thread{

    @Override
    public void run() {
        System.out.println("创建线程");
    }
}
