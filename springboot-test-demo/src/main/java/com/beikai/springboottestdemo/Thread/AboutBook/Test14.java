package com.beikai.springboottestdemo.Thread.AboutBook;

/**
 * @ClassName Test14
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/14 10:58
 * @Version 1.0
 **/
public class Test14 {
    public static void main(String[] args) {
        try {
            MyThread14 myThread14 = new MyThread14();
            myThread14.start();
            Thread.sleep(2000);
            // 线程中断
            myThread14.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class MyThread14 extends Thread {
    private int i = 0;
    @Override
    public void run() {
        super.run();
        while (true) {
            System.out.println("i = " + (i++));
            if (this.isInterrupted()) {
                System.out.println("线程停止了 ... ");
                return;
            }
        }
    }
}
