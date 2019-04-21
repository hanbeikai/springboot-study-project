package com.beikai.springboottestdemo.Thread.AboutBook;

/**
 * @ClassName Test10
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/13 22:31
 * @Version 1.0
 **/
public class Test10 {
    public static void main(String[] args) throws InterruptedException {
        try {
            MyThread10_2 myThread10 = new MyThread10_2();
            myThread10.start();
            Thread.sleep(1000);
            myThread10.interrupt();
        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end");

        //method4();
        //method1();
        //method2();
        //method3();

    }

    private static void method4() {
        try {
            MyThread10 myThread10 = new MyThread10();
            myThread10.start();
            Thread.sleep(1000);
            myThread10.interrupt();
        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end");
    }

    private static void method3() {
        try {
            MyThread10 myThread10 = new MyThread10();
            myThread10.start();
            // 中的线程
            myThread10.interrupt();
            // 查看线程是否终止
            System.out.println("线程是否停止1 : " + myThread10.isInterrupted());
            System.out.println("线程是否停止2 : " + myThread10.isInterrupted());
        } catch (Exception e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end");
    }

    private static void method2() {
        Thread.currentThread().interrupt();
        // 查看线程是否终止
        System.out.println("线程是否停止1 : " + Thread.interrupted());
        System.out.println("线程是否停止2 : " + Thread.interrupted());
    }

    private static void method1() {
        try {
            MyThread10 myThread10 = new MyThread10();
            myThread10.start();
            // 中的线程
            myThread10.interrupt();
            // 查看线程是否终止
            System.out.println("线程是否停止1 : " + Thread.interrupted());
            System.out.println("线程是否停止2 : " + Thread.interrupted());
        } catch (Exception e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end");
    }
}
class MyThread10 extends Thread{
    @Override
    public void run() {
        super.run();
        try {
            for (int i = 0; i < 1000000; i++) {
                if (this.isInterrupted()){
                    System.out.println("已经中断了...");
                    throw new InterruptedException();
                }
                System.out.println("i=" + (i+1));
            }
            System.out.println("thread run end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
class MyThread10_2 extends Thread{
    @Override
    public void run() {
        super.run();
        try {
            System.out.println("thread begin : ");
            Thread.sleep(200000);
            System.out.println("thread end : ");
        } catch (InterruptedException e) {
            System.out.println("thread catch...");
            e.printStackTrace();
        }

    }
}
