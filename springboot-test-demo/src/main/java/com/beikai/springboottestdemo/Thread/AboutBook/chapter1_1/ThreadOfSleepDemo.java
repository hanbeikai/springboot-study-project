package com.beikai.springboottestdemo.Thread.AboutBook.chapter1_1;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/20
 * Time: 7:51 上午
 * Description: No Description
 */
public class ThreadOfSleepDemo {

    public static void main(String[] args) {
        MyThread01 myThread01 = new MyThread01();
        myThread01.setName("线程- ");
        System.out.println("main begin " + System.currentTimeMillis());
        myThread01.start();
        System.out.println("main end " + System.currentTimeMillis());
        System.out.println(myThread01.getId());
    }

    private static class MyThread01 extends Thread{
        @Override
        public void run() {
            System.out.println("beging : " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end    : " + Thread.currentThread().getName());
        }
    }
}

