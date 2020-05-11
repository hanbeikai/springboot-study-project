package com.beikai.springboottestdemo.Thread.AboutBook.chapter2_1;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/9
 * Time: 5:23 下午
 * Description: Volatile 非原子性的特性 - 通过synchronize + static 替代 Volatile 关键字
 * <p>
 * 线程安全性有两个方面,一方面是线程的原子性,另一方面是线程的可见性,同步机制是围绕着这两个方面来保证线程的安全性的
 * Volatile 关键字 虽然保证了线程的可见性,但是不能保证原子性
 */
public class VolatileOfThreadDemo04_2 {

    public static void main(String[] args) {

        MyThread05[] myThread04s = new MyThread05[100];
        for (int i = 0; i < myThread04s.length; i++) {
            myThread04s[i] = new MyThread05();
        }

        for (int i = 0; i < myThread04s.length; i++) {
            MyThread05 thread04 = myThread04s[i];
            thread04.start();
        }

        System.out.println("线程启动完成 !");


        /**{
         *  线程计算结果 :
         *
         * count=100
         * count=200
         * count=300
         * count=400
         * count=500
         * count=600
         * count=700
         * count=800
         * count=900
         * count=1000
         * count=1100
         * count=1200
         * count=1300
         * count=1400
         * count=1500
         * count=1600
         * ...
         *
         * 从上面打印可以看出,多线程计算的结果没有错误,
         *
         */
    }
}

class MyThread05 extends Thread {

    // 如果使用了 synchronize 和 static 关键字,就可以不使用Volatile来修饰变量,synchronize 会保证线程数据的同步
    public static int count;

    // 使用关键字 synchronize 需要配置 static 关键字来使用,这样锁的就是 整个 MyThread05.class 类,可以保证数据的同步
    synchronized private static void addCount() {
        for (int i = 0; i < 100; i++) {
            count++;
        }

        System.out.println("count=" + count);

    }

    @Override
    public void run() {
        addCount();
    }
}
