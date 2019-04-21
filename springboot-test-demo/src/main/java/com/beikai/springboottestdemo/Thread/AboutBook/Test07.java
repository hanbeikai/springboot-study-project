package com.beikai.springboottestdemo.Thread.AboutBook;

/**
 * @ClassName Test05
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/13 19:07
 * @Version 1.0
 **/
public class Test07 {
    public static void main(String[] args) {
    MyThread007 myThread007 = new MyThread007();
        System.out.println("begin : " + System.currentTimeMillis());
        myThread007.start();
        System.out.println("end : " + System.currentTimeMillis());

    }
}

class MyThread007 extends Thread{
    private int i = 5;
    @Override
    public void run() {
        super.run();
        for (int j = 0; j < 10; j++) {
            System.out.println("打印 " + j);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
