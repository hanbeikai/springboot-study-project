package com.beikai.springboottestdemo.Thread.AboutBook.chapter2_1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/6
 * Time: 8:18 上午
 * Description: 原子类也并不完全安全
 * 原子类本身是安全的,但是如果涉及到的方法里面有多个逻辑处理,原子类与原子类之间是不安全的
 * <p>
 * 可以使用countDownLatch 分多个线程处理逻辑,最后等所有线程处理完成了,再一起提交
 */
public class VolatileOfThreadDemo06 {

    public static void main(String[] args) {
        VolatileService06 volatileService06 = new VolatileService06();
        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            VolatileThread06 volatileThread06 = new VolatileThread06(volatileService06, "thread-" + i, countDownLatch);
            volatileThread06.start();
        }

        try {
            System.out.println("等待所有线程开始执行完成...");
            countDownLatch.await();
            System.out.println("最后的计算结果是 : " + VolatileService06.atomicLong.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        /**{
         * 打印的结果是 :
         *  thread-0 添加100之后的值为 100
         * thread-1 添加100之后的值为 200
         * thread-2 添加100之后的值为 302
         * 等待所有线程开始执行完成...
         * thread-3 添加100之后的值为 402
         * thread-4 添加100之后的值为 503
         * 最后的计算结果是 : 505
         *
         * 从打印结果可以看出,每次打印应该是 101 的倍数,但是实际上有几个是整百,出现这样的原因是原子类的方法是原子性的,但是方法和方法之间不是原子性的
         * 在
         *       System.out.println(Thread.currentThread().getName() + " 添加100之后的值为 " + atomicLong.addAndGet(100));
         *       atomicLong.addAndGet(1);
         *
         *  这两个方法中间出现了非原子性操作
         *
         *  解决的办法是使用同步关键字 synchronize 来
         *
         *
         */
    }

}

class VolatileThread06 extends Thread {

    private VolatileService06 volatileService06;
    private CountDownLatch countDownLatch;

    public VolatileThread06(VolatileService06 volatileService06, String name, CountDownLatch countDownLatch) {
        super(name);
        this.volatileService06 = volatileService06;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            volatileService06.addNum();
        } finally {
            countDownLatch.countDown();
        }

    }
}

class VolatileService06 {

    public static AtomicLong atomicLong = new AtomicLong();

    // 解决原子类方法之间非原子性的情况 是使用同步关键字修饰方法
    synchronized public void addNum() {
        System.out.println(Thread.currentThread().getName() + " 添加100之后的值为 " + atomicLong.addAndGet(100));
        atomicLong.addAndGet(1);
    }
}
