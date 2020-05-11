package com.beikai.springboottestdemo.Thread.AboutBook.chapter2_1;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/9
 * Time: 6:23 下午
 * Description: 使用原子类进行 i++ 操作
 *
 *   i++ 操作保证原子性的方式有两种
 *
 *       1 使用关键字 synchronize 和 static 修饰
 *       2 使用原子类,原子类的内部操作都是原子性的,可以在没有锁的前提下保证线程的安全
 *
 *
 *       原子性 :
 *          原子操作是不可分割的,没有线程可以打断或者检测原子操作中的变量
 *
 */
public class VolatileOfThreadDemo05 {

    public static void main(String[] args) {
        AtomicAddCountThread atomicAddCountThread = new AtomicAddCountThread();
        Thread thread = new Thread(atomicAddCountThread);
        thread.start();
        Thread thread2 = new Thread(atomicAddCountThread);
        thread2.start();
        Thread thread3 = new Thread(atomicAddCountThread);
        thread3.start();
        Thread thread4 = new Thread(atomicAddCountThread);
        thread4.start();
        Thread thread5 = new Thread(atomicAddCountThread);
        thread5.start();
        Thread thread6 = new Thread(atomicAddCountThread);
        thread6.start();


        /**{
         * 1
         * 4
         * 6
         * 7
         * 8
         * 3
         * 11
         * 12
         * 13
         * 15
         * 16
         * 17
         * 18
         * 2
         * 20
         * 21
         * 22
         * 23
         * 24
         * 25
         * ...
         * 59993
         * 59994
         * 59995
         * 59996
         * 59997
         * 59998
         * 59999
         * 60000
         *
         *  从打印可以看出,没有出现打印错误,即数字重复或缺少的情况,所以可以使用原子类自带的方法是可以保证线程安全的
         *
         */
    }
}

class AtomicAddCountThread extends Thread{
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            // 调用增加的方法
            System.out.println(atomicInteger.incrementAndGet());
        }
    }
}
