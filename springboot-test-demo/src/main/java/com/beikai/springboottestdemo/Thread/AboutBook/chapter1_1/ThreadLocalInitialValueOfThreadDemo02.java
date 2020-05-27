package com.beikai.springboottestdemo.Thread.AboutBook.chapter1_1;

import lombok.SneakyThrows;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/27
 * Time: 8:42 上午
 * Description: 设置threadLocal初始化值 以及 隔离性
 */
public class ThreadLocalInitialValueOfThreadDemo02 {

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            System.out.println("在main线程中取到的值是 : " + MyTools.myThreadLocal2.get());
            Thread.sleep(100);
        }


        Thread.sleep(5000);

        InitValue initValue = new InitValue();
        initValue.start();


        /**{
         *在main线程中取到的值是 : 1590540826723
         * 在main线程中取到的值是 : 1590540826723
         * 在main线程中取到的值是 : 1590540826723
         * 在main线程中取到的值是 : 1590540826723
         * 在main线程中取到的值是 : 1590540826723
         * 在main线程中取到的值是 : 1590540826723
         * 在threadLocal线程线程中取到的值是 : 1590540832746
         * 在threadLocal线程线程中取到的值是 : 1590540832746
         * 在threadLocal线程线程中取到的值是 : 1590540832746
         *
         *
         * 从打印来看,不同线程从同一个localthread 获取到的是不同的值
         */
    }
}


class MyThreadLocal2 extends ThreadLocal{
    @Override
    protected Object initialValue() {
        return System.currentTimeMillis();
    }
}

class MyTools {
    public static MyThreadLocal2 myThreadLocal2 = new MyThreadLocal2();
}

class InitValue extends Thread {

    @SneakyThrows
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("在threadLocal线程线程中取到的值是 : " + MyTools.myThreadLocal2.get());
            Thread.sleep(1000);
            
        }
    }
}