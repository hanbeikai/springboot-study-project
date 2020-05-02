package com.beikai.springboottestdemo.Thread.AboutBook.chapter1_1;

/**
 * @ClassName Test16
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/14 11:20
 * @Version 1.0
 **/
public class Test16 {
    public static void main(String[] args) {
        try {
            final Synchr synchr = new Synchr();
            Thread thread = new Thread(() -> {
                synchr.print();
            });
            thread.setName("a");
            thread.start();
            Thread.sleep(1000);

            Thread thread1 = new Thread(() -> {
                System.out.println("线程2启动了 ... 但进入不了 synchr中 ...");
                System.out.println("因为print方法已经被a线程锁定了,并且永远suspend 暂停了 ...");
                synchr.print();
            });
            thread1.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Synchr {
    public synchronized void print() {
        System.out.println("begin ... ");
        if (Thread.currentThread().getName().equals("a")) {
            System.out.println("线程a 永远 suspend 了");
            // 暂停线程
            Thread.currentThread().suspend();
        }
    }
}
