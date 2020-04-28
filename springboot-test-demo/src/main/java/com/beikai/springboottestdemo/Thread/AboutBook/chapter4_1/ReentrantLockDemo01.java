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
public class ReentrantLockDemo01 {



    public static void main(String[] args) {
        ServiceDemo serviceDemo = new ServiceDemo();
        MyThread myThread1 = new MyThread(serviceDemo);
        MyThread myThread2 = new MyThread(serviceDemo);
        MyThread myThread3 = new MyThread(serviceDemo);
        MyThread myThread4 = new MyThread(serviceDemo);
        MyThread myThread5 = new MyThread(serviceDemo);
        MyThread myThread6 = new MyThread(serviceDemo);
        MyThread myThread7 = new MyThread(serviceDemo);

        myThread1.run();
        myThread2.run();
        myThread3.run();
        myThread4.run();
        myThread5.run();
        myThread6.run();
        myThread7.run();
    }

}


class MyThread extends Thread{

    private ServiceDemo serviceDemo;
    public MyThread(ServiceDemo reentrantLockDemo01) {
        super();
        this.serviceDemo = reentrantLockDemo01;
    }

    @Override
    public void run() {
        serviceDemo.testMethod();
    }
}

class ServiceDemo{

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

    public void testMethod() {
        lock.lock();

        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("threadname -> " + Thread.currentThread().getName() + "  " + i);
            }
        } finally {
            lock.unlock();
        }
    }


}