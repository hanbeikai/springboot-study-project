package com.beikai.springboottestdemo.Thread.AboutBook.chapter2_1;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/9
 * Time: 5:23 下午
 * Description: Volatile 非原子性的特性
 * <p>
 * 线程安全性有两个方面,一方面是线程的原子性,另一方面是线程的可见性,同步机制是围绕着这两个方面来保证线程的安全性的
 * Volatile 关键字 虽然保证了线程的可见性,但是不能保证原子性
 */
public class VolatileOfThreadDemo04 {

    public static void main(String[] args) {

        MyThread04[] myThread04s = new MyThread04[100];
        for (int i = 0; i < myThread04s.length; i++) {
            myThread04s[i] = new MyThread04();
        }

        for (int i = 0; i < myThread04s.length; i++) {
            MyThread04 thread04 = myThread04s[i];
            thread04.start();
        }

        System.out.println("线程启动完成 !");


        /**{
         *  线程计算结果 :
         *
         *  ...
         *  count=4900
         * count=5000
         * count=5100
         * count=5200
         * count=5300
         * count=5400
         * count=5500
         * count=5600
         * count=5700
         * count=5800
         * count=5900
         * count=6000
         * count=6100
         * count=6249
         * count=6349
         * count=6249
         * count=6649
         * count=6549
         * count=6449
         * count=6749
         * count=6849
         * count=6949
         * ...
         *
         * 从上面的打印可以看出,本应该是100的倍数,但是有 xx49 的格式数字打印,原因就是被Volatile关键字修饰的变量没有原子性
         *
         */
    }
}

class MyThread04 extends Thread {

    volatile public static int count;
    private static void addCount() {
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
