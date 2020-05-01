package com.beikai.springboottestdemo.Thread.AboutBook.chapter4_1;

import java.util.Calendar;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/1
 * Time: 7:37 下午
 * Description: condition.awaitUntil  方法  设置线程的等待时间
 */
public class ReentrantLockAboutAwaitUntil {

    public static void main(String[] args) throws InterruptedException {
        AwaitUntilService awaitUntilService = new AwaitUntilService();
        Thread thread = new Thread(() -> {
            awaitUntilService.awaitSevice();
        });
        Thread thread3 = new Thread(() -> {
            awaitUntilService.awaitSevice2();
        });
        Thread thread2 = new Thread(() -> {
            awaitUntilService.singleAll();
        });

        thread.setName("thread-a");
        thread.start();

        Thread.sleep(500);
        thread3.setName("thread-a3");
        thread3.start();

        Thread.sleep(1000);
        thread2.setName("thread-a2");
        thread2.start();

        /**{
         *  使用  condition.awaitUntil(instance.getTime());  可以类似与 thread.sleep() 等情况，线程处于等待情况
         *
         *      begin time : 1588333671553
         *      end   time : 1588333681549
         *
         *   Thread.sleep(10000); 会占有锁
         *
         *   可以使用 condition.awaitUntil 占用线程时间，但是会暂时放开锁，如果又线程调用了 signal()
         *   被condition.awaitUntil 等待的线程会被提前释放
         *
         */
    }
}


class AwaitUntilService{
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    void awaitSevice(){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND,10);
        lock.lock();
        try {
            System.out.println("begin time : " + System.currentTimeMillis() +"  " + Thread.currentThread().getName());
            //condition.awaitUntil(instance.getTime());
            Thread.sleep(10000);
            System.out.println("end   time : " + System.currentTimeMillis() +"  " + Thread.currentThread().getName());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("线程 " + Thread.currentThread().getName() + " 释放锁");
        }
    }
    void awaitSevice2(){

        lock.lock();
        try {
            Calendar instance = Calendar.getInstance();
            instance.add(Calendar.SECOND,5);
            System.out.println("begin time : " + System.currentTimeMillis() +"  " + Thread.currentThread().getName()) ;
            condition.awaitUntil(instance.getTime());
            System.out.println("end   time : " + System.currentTimeMillis() +"  " + Thread.currentThread().getName());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("线程 " + Thread.currentThread().getName() + " 释放锁");
        }
    }

    void singleAll(){
        lock.lock();
        try {
            System.out.println("begin time : " + System.currentTimeMillis() +"  " + Thread.currentThread().getName());

            condition.signalAll();

            System.out.println("end   time : " + System.currentTimeMillis() +"  " + Thread.currentThread().getName());
        }finally {
            lock.unlock();
            System.out.println("线程 " + Thread.currentThread().getName() + " 释放锁");
        }
    }
}