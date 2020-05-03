package com.beikai.springboottestdemo.Thread.AboutBook.chapter7_1;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/3
 * Time: 6:20 下午
 * Description: 使线程 具有 有序性
 * 正常情况下,多个线程之间的执行顺序是 不定的 ,但是可以通过改造代码的情况下实现有序
 *
 *      线程中使用一个共享的对象用来在线程之间传递信息,volatile
 *      对每一个线程设置一个参数,用来区别不同的线程,同时用来计算,限制不同线程的执行顺序
 *
 *      满足条件 :
 *          每一次一个线程执行成功后,对共享的数据加1,对当前线程所在的打印次数加1
 *          如果线程内打印次数超过3次,跳出循环
 *          唤醒所有线程
 *
 *      不满足条件 :
 *          线程等待唤醒
 *
 *
 */
public class ThreadGroupDemo09 {

    public static void main(String[] args) {
        Object lock = new Object();
        MyThread01 myThread01 = new MyThread01(lock,"a",1);
        MyThread01 myThread011 = new MyThread01(lock,"b",2);
        MyThread01 myThread012 = new MyThread01(lock,"c",0);

        myThread01.start();
        myThread011.start();
        myThread012.start();
    }
}


class MyThread01 extends Thread {
    private Object lock;
    private String showCode;
    private int showNumPosi;
    private int printCount = 0;
    volatile private static int addNum = 1;

    public MyThread01(Object lock, String showCode, int showNumPosi) {
        this.lock = lock;
        this.showCode = showCode;
        this.showNumPosi = showNumPosi;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                // 如果计数 除 3 余数是 对应线程 的标识号
                while (true) {
                    if (addNum % 3 == showNumPosi) {
                        System.out.println("当前线程为 : " + Thread.currentThread().getName()
                                + " 对应的线程code为 : " + showCode
                                + " 对应的线程数是 : " + showNumPosi
                        );

                        // 满足条件后,唤醒等待的线程
                        lock.notifyAll();
                        // 计数添加
                        printCount++;
                        addNum++;

                        if (3 == printCount) {
                            break;
                        }

                    } else {
                        lock.wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
