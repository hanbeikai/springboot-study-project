package com.beikai.springboottestdemo.Thread.AboutBook.chapter1_1;

/**
 * @ClassName Test15
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/14 11:10
 * @Version 1.0
 **/
public class Test15 {
    public static void main(String[] args) {
        try {
            MyThread15 myThread15 = new MyThread15();
            myThread15.start();
            Thread.sleep(1000);

            // a 段
            myThread15.suspend();
            System.out.println("a = " + System.currentTimeMillis() + "  id = " + myThread15.getI());
            Thread.sleep(5000);
            System.out.println("a = " + System.currentTimeMillis() + "  id = " + myThread15.getI());
            // b 段
            myThread15.resume();
            System.out.println("b = " + System.currentTimeMillis() + "  id = " + myThread15.getI());
            Thread.sleep(5000);
            System.out.println("b = " + System.currentTimeMillis() + "  id = " + myThread15.getI());
            // c 段
            myThread15.suspend();
            System.out.println("c = " + System.currentTimeMillis() + "  id = " + myThread15.getI());
            Thread.sleep(5000);
            System.out.println("c = " + System.currentTimeMillis() + "  id = " + myThread15.getI());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread15 extends Thread {
    private long i = 0;

    public long getI() {
        return i;
    }

    public void setI(long i) {
        this.i = i;
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            i++;
        }
    }
}
