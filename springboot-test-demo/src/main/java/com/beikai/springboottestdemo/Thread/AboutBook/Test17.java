package com.beikai.springboottestdemo.Thread.AboutBook;

/**
 * @ClassName Test17
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/14 11:32
 * @Version 1.0
 **/
public class Test17 {
    public static void main(String[] args) {

        try {
            MyThread17 myThread17 = new MyThread17();
            myThread17.start();
            Thread.sleep(1000);
            // 暂停线程
            myThread17.suspend();
            System.out.println("main end ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread17 extends Thread {
    private long i = 0;
    @Override
    public void run() {
        super.run();
        while (true) {
            i++;
            System.out.println(i);
        }
    }
}
