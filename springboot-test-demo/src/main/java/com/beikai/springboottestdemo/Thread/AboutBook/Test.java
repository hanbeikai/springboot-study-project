package com.beikai.springboottestdemo.Thread.AboutBook;


/**
 * @ClassName Test
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/13 11:02
 * @Version 1.0
 **/
public class Test {

    public static void main(String[] args) throws InterruptedException {

        System.out.println(Thread.currentThread().getName());

        switchMethod(3);

    }

    /**
     * 选择方法
     *
     * @param i
     */
    private static void switchMethod(int i) throws InterruptedException {
        switch (i) {
            case 1:
                // 关于测试线程 睡眠 方法
                aboutThreadSleep();
                break;
            case 2:
                // 关于测试线程是否存活
                aboutThreadIsAlive();
                break;
            case 3:
                // 关于 守护线程
                aboutThreadDaemon();
                break;
            default:
                break;
        }
    }

    /**
     * 关于守护线程
     *  java 中线程有两种, 一种是用户线程,一种是守护线程
     *  我们通过new thread().start() 的线程是用户线程
     *  守护线程 是通过用户线程的 setDaemon()方法设置的,
     *
     *  守护线程可以理解为未用户线程服务的,当jvm中没有非守护线程后,守护线程会随着jvm一同结束工作
     */
    private static void aboutThreadDaemon() throws InterruptedException {
        MyThread4 myThread4 = new MyThread4();
        // 设置为守护线程
        myThread4.setDaemon(true);
        myThread4.start();
        System.out.println("当前线程 : " + Thread.currentThread().getName());
        System.out.println("当前线程状态 : " + Thread.currentThread().getState());
        System.out.println("当前线程是否存活 : " + Thread.currentThread().isAlive());
        Thread.sleep(2000);
        System.out.println("当前线程 : " + Thread.currentThread().getName());
        System.out.println("当前线程状态 : " + Thread.currentThread().getState());
        System.out.println("当前线程是否存活 : " + Thread.currentThread().isAlive());
        System.out.println("当前线程是否存活 : " + Thread.currentThread().isAlive());
        System.out.println("我离开,myThread4对象也不在打印了,结束了");

    }

    /**
     * 线程的存活, 指的是正在运行或者准备好运行的状态
     */
    private static void aboutThreadIsAlive() throws InterruptedException {
        MyThread2 myThread = new MyThread2();

        Thread thread = new Thread(myThread);
        System.out.println("--------------------------------------");
        System.out.println("begin : " + thread.isAlive());

        System.out.println("当前线程状态 : " + thread.getState());

        myThread.setName("线程0");
        thread.setName("线程1");
        thread.start();

        //Thread.sleep(1000);
        System.out.println("--------------------------------------");
        System.out.println("end : " + thread.isAlive());
    }

    /**
     * 测试睡眠方法
     *  thread.sleep() 睡眠的是当前的线程, 是在线程内部使用的,也就是说在那个线程的内部使用,睡眠的就是那个线程, 就该例子而言, 睡眠的是主线程
     */
    private static void aboutThreadSleep() throws InterruptedException {

        MyThread myThread = new MyThread();
        myThread.start();

        System.out.println("---------------------------");

        MyThread3 myThread3 = new MyThread3();
        myThread3.start();

        System.out.println("线程1 状态 : " + myThread.isAlive());
        System.out.println("线程3 状态 : " + myThread3.isAlive());

        try {
            System.out.println("=======================================");
            Thread.sleep(10000);

            System.out.println("线程1 状态 (后) : " + myThread.isAlive());
            System.out.println("线程3 状态 (后) : " + myThread3.isAlive());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "--" + i);
             if (i == 50){
                 Thread.sleep(10000);
             }
            
        }

    }
}

class MyThread2 extends Thread {

    public MyThread2() {

        System.out.println("MyThread 构造器 begin");
        System.out.println("当前线程状态 构造器 : " + Thread.currentThread().getState());
        System.out.println("Thread.currentThread().getName() : " + Thread.currentThread().getName());
        System.out.println("Thread.currentThread().isAlive() : " + Thread.currentThread().isAlive());
        System.out.println("this.isAlive : " + this.isAlive());
        System.out.println("this.getName :" + this.getName());
        System.out.println("MyThread 构造器 end");
    }

    @Override
    public void run() {

        System.out.println("-------------------------------------------------");
        System.out.println("MyThread (run) begin");
        System.out.println("当前线程状态 (run) : " + Thread.currentThread().getState());
        System.out.println("Thread.currentThread().getName() (run) : " + Thread.currentThread().getName());
        System.out.println("Thread.currentThread().isAlive() (run) : " + Thread.currentThread().isAlive());
        System.out.println("this.isAlive (run) : " + this.isAlive());
        System.out.println("this.getName (run) :" + this.getName());
        System.out.println("MyThread (run) end");
    }
}

class MyThread extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("线程1 执行的结果是 : " + i);
           /* try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }
}

class MyThread3 extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("线程2 执行的结果是 : " + i);
           /* try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }
}

class MyThread4 extends Thread{
    private int i = 0;
    @Override
    public void run() {
        while (true){
            i++;
            System.out.println("当前i的值是 : " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}