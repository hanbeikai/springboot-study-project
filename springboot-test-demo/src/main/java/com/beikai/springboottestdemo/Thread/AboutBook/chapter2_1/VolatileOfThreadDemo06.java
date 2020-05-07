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
 *
 *  可以使用countDownLatch 分多个线程处理逻辑,最后等所有线程处理完成了,再一起提交
 */
public class VolatileOfThreadDemo06 {

    public static void main(String[] args) {
        VolatileService06 volatileService06 = new VolatileService06();
        CountDownLatch countDownLatch = new CountDownLatch(100);

        for (int i = 0; i < 100; i++) {
            VolatileThread06 volatileThread06 = new VolatileThread06(volatileService06,"thread-" + i,countDownLatch);
            volatileThread06.start();
        }

        try {
            System.out.println("等待所有线程开始执行完成...");
            countDownLatch.await();
            System.out.println("最后的计算结果是 : " + VolatileService06.atomicLong.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class VolatileThread06 extends Thread {

    private VolatileService06 volatileService06;
    private CountDownLatch countDownLatch;

    public VolatileThread06(VolatileService06 volatileService06, String name,CountDownLatch countDownLatch) {
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

    void addNum() {
        System.out.println(Thread.currentThread().getName() + " 添加100之后的值为 " + atomicLong.addAndGet(100));
        atomicLong.addAndGet(1);
    }
}
