package com.beikai.springboottestdemo.Thread.AboutBook.chapter4_1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/4/27
 * Time: 9:10 上午
 * Description: No Description
 */
public class ReentrantLockDemo02 {


    public static void main(String[] args) {

        /**
         * 功能描述:
         * 〈从此实验可以看出，调用lock.lock() 代码的线程持有了一个对象监视器，其他线程只有等待锁被释放时再次争抢，效果和同步关键字相同（synchronized），线程之间顺序进行，必须等待另一个完成后才可以获取锁〉
         *
         * @param args
         * @return : void
         * @author : hbk
         * @date : 2020/4/28
         */
        ServiceDemo2 serviceDemo = new ServiceDemo2();
        MyThreadA myThreadA = new MyThreadA(serviceDemo);
        myThreadA.setName("a");
        MyThreadA myThreadAA = new MyThreadA(serviceDemo);
        myThreadAA.setName("aa");
        MyThreadB myThreadB = new MyThreadB(serviceDemo);
        myThreadB.setName("b");
        MyThreadBB myThreadBB = new MyThreadBB(serviceDemo);
        myThreadBB.setName("bb");



        myThreadA.start();
        myThreadAA.start();
        myThreadB.start();
        myThreadBB.start();
    }

}


class MyThreadA extends Thread {

    private ServiceDemo2 serviceDemo;

    public MyThreadA(ServiceDemo2 reentrantLockDemo01) {
        super();
        this.serviceDemo = reentrantLockDemo01;
    }

    @Override
    public void run() {
        serviceDemo.testMethodA();
    }
}
class MyThreadAA extends Thread {

    private ServiceDemo2 serviceDemo;

    public MyThreadAA(ServiceDemo2 reentrantLockDemo01) {
        super();
        this.serviceDemo = reentrantLockDemo01;
    }

    @Override
    public void run() {
        serviceDemo.testMethodA();
    }
}
class MyThreadB extends Thread {

    private ServiceDemo2 serviceDemo;

    public MyThreadB(ServiceDemo2 reentrantLockDemo01) {
        super();
        this.serviceDemo = reentrantLockDemo01;
    }

    @Override
    public void run() {
        serviceDemo.testMethodB();
    }
}
class MyThreadBB extends Thread {

    private ServiceDemo2 serviceDemo;

    public MyThreadBB(ServiceDemo2 reentrantLockDemo01) {
        super();
        this.serviceDemo = reentrantLockDemo01;
    }

    @Override
    public void run() {
        serviceDemo.testMethodB();
    }
}

class ServiceDemo2 {

    /**
     * 功能描述:
     * 〈〉
     *
     * @param null
     * @return :
     * @author : hbk
     * @date : 2020/4/28  多线程中，可以使用 ReentrantLock 来实现线程之间同步互斥的效果，跟同步关键字相同的效果，但是在拓展上更加强大，比如使用嗅探锁定，多路分支通知等功能，并且使用上更加灵活。
     */
    private Lock lock = new ReentrantLock();

    public void testMethodA() {

        lock.lock();
        try {

            System.out.println("线程开始 a ---》 " + Thread.currentThread().getName() + "   时间 : " + System.currentTimeMillis());


            Thread.sleep(1000);


            System.out.println("线程结束 a ---》 " + Thread.currentThread().getName() + "   时间 : " + System.currentTimeMillis());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void testMethodB() {
        lock.lock();
        try {

            System.out.println("线程开始 b ---》 " + Thread.currentThread().getName() + "   时间 : " + System.currentTimeMillis());


            Thread.sleep(1000);


            System.out.println("线程结束 b ---》 " + Thread.currentThread().getName() + "   时间 : " + System.currentTimeMillis());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}