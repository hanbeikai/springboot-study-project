package com.beikai.springboottestdemo.Thread.AboutBook;

/**
 * @ClassName Test22
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/14 12:44
 * @Version 1.0
 **/
public class Test22 {
    public static void main(String[] args) {

        try {
            MyThread22_1 myThread22_1 = new MyThread22_1();
            myThread22_1.setPriority(Thread.NORM_PRIORITY - 3);
            myThread22_1.start();

            MyThread22_2 myThread22_2 = new MyThread22_2();
            myThread22_2.setPriority(Thread.NORM_PRIORITY + 3);
            myThread22_2.start();

            // 当前线程是 主 线程的
            Thread.sleep(1000);
            myThread22_1.stop();
            myThread22_2.stop();

            System.out.println("线程1 : " + myThread22_1.getCount());
            System.out.println("线程2 : " + myThread22_2.getCount());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
class MyThread22_1 extends Thread{
    private int count = 0;
    public int getCount() {
        return count;
    }
    @Override
    public void run() {
        super.run();
        while (true){
            count++;
        }
    }
}
class MyThread22_2 extends Thread{
    private int count = 0;
    public int getCount() {
        return count;
    }
    @Override
    public void run() {
        super.run();
        while (true){
            count++;
        }
    }
}
