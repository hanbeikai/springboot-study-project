package com.beikai.springboottestdemo.Thread.AboutBook.chapter4_1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/1
 * Time: 7:19 下午
 * Description: lock.awaitUninterruptibly() 方法
 */
public class ReentrantLockAboutAwaitUninterruptibly {

    public static void main(String[] args) throws InterruptedException {
        AwaitUninterruptiblyService awaitUninterruptiblyService = new AwaitUninterruptiblyService();
        Thread thread = new Thread(() -> {
            awaitUninterruptiblyService.awaitService();
        });

        thread.setName("thread-a");
        thread.start();

        Thread.sleep(1000);
        thread.interrupt();


        /**{
         *  在 condition 使用 await 的情况下，直接使用 interrupt，会导致 报错：
         *      wait begin ...
         *      java.lang.InterruptedException
         * 	    at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.reportInterruptAfterWait(AbstractQueuedSynchronizer.java:2014)
         * 	    at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2048)
         * 	    at com.beikai.springboottestdemo.Thread.AboutBook.chapter4_1.AwaitUninterruptiblyService.awaitService(ReentrantLockAboutAwaitUninterruptibly.java:39)
         * 	    at com.beikai.springboottestdemo.Thread.AboutBook.chapter4_1.ReentrantLockAboutAwaitUninterruptibly.lambda$main$0(ReentrantLockAboutAwaitUninterruptibly.java:18)
         * 	    at java.lang.Thread.run(Thread.java:748)
         *      catch ...
         *
         *  但是使用 condition.awaitUninterruptibly() 不会异常
         *
         *      wait begin ...  停止了
         *
         */
    }
}


class AwaitUninterruptiblyService{
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    void awaitService(){
        lock.lock();
        try {
            System.out.println("wait begin ... ");

            // 使用  thread.interrupt(); 的情况下会出现异常
            //condition.await();

            // 使用  使用  thread.interrupt(); 的情况下会出现异常 不会异常
            condition.awaitUninterruptibly();

            System.out.println("wait end ... ");
        } finally {
            lock.unlock();
        }
    }
}