package com.beikai.springboottestdemo.Thread.AboutBook;

/**
 * @ClassName Test11
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/14 10:15
 * @Version 1.0
 **/
public class Test11 {

    public static void main(String[] args) {
        try {
            MyThread11 myThread11 = new MyThread11();
            myThread11.start();
            Thread.sleep(1000);
            // 使用线程终止
            myThread11.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread11 extends Thread {
    private int i = 0;
    @Override
    public void run() {
        super.run();
        try {
            while (true) {
                i++;
                System.out.println("i= " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("thread catch...");
            e.printStackTrace();
        }
    }
}