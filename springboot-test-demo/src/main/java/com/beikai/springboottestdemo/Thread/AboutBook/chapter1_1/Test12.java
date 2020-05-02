package com.beikai.springboottestdemo.Thread.AboutBook.chapter1_1;

/**
 * @ClassName Test12
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/14 10:24
 * @Version 1.0
 **/
public class Test12 {
    public static void main(String[] args) {
        MyThread12 myThread12 = new MyThread12();
        myThread12.start();
    }
}
class MyThread12 extends Thread{
    @Override
    public void run() {
        super.run();
        try {
            System.out.println("begin ... ");
            this.stop();
            System.out.println("end ... ");
        } catch (Exception e) {
            System.out.println("thread catch ...");
            e.printStackTrace();
        }
    }
}
