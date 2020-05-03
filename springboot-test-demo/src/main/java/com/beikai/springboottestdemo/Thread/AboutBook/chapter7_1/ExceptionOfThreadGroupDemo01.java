package com.beikai.springboottestdemo.Thread.AboutBook.chapter7_1;

import lombok.SneakyThrows;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/3
 * Time: 9:08 下午
 * Description: 线程池组内部异常处理
 * <p>
 * 实现线程组中如果有一个线抛出异常,线程组内所有线程全都停止运行
 */
public class ExceptionOfThreadGroupDemo01 {

    public static void main(String[] args) {
        // 测试demo  用来测试 线程组中如果有一个线程抛出异常,不会影响到其他线程
       // testDemo01();

        // 解决的方法是 自定义 线程组,重写 uncaughtException() 方法,用来处理捕获的异常
        testDemo02();
    }

    // 测试 线程组中如果有一个线程抛出异常,线程组内所有线程都会被停止
    private static void testDemo02() {
        // 创建我的线程组
        MyThreadGroup my = new MyThreadGroup("我的线程组-重写了线程异常");
        MyThread08[] threads = new MyThread08[10];
        for (int i = 0; i < threads.length; i++) {
            MyThread08 thread = new MyThread08(my, "线程" + (i + 1), "1");
            threads[i] = thread;
            threads[i].start();
        }

        MyThread08 myThread07 = new MyThread08(my, "报错线程", "a");
        myThread07.start();


        /**
         * 陷入了死循环中 : 线程1
         * 陷入了死循环中 : 线程5
         * 陷入了死循环中 : 线程4
         * 陷入了死循环中 : 线程3
         * 陷入了死循环中 : 线程2
         * 陷入了死循环中 : 线程7
         * 陷入了死循环中 : 线程6
         * 陷入了死循环中 : 线程8
         * 陷入了死循环中 : 线程9
         * 陷入了死循环中 : 线程10
         * Exception in thread "报错线程" java.lang.NumberFormatException: For input string: "a"
         * 	at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
         * 	at java.lang.Integer.parseInt(Integer.java:580)
         * 	at java.lang.Integer.parseInt(Integer.java:615)
         * 	at com.beikai.springboottestdemo.Thread.AboutBook.chapter7_1.MyThread08.run(ExceptionOfThreadGroupDemo01.java:111)
         * Exception in thread "线程4" Exception in thread "线程2" Exception in thread "线程6" Exception in thread "线程8" Exception in thread "线程10" java.lang.InterruptedException: sleep interrupted
         * 	at java.lang.Thread.sleep(Native Method)
         * 	at com.beikai.springboottestdemo.Thread.AboutBook.chapter7_1.MyThread08.run(ExceptionOfThreadGroupDemo01.java:114)
         * Exception in thread "线程9" Exception in thread "线程7" Exception in thread "线程1" java.lang.InterruptedException: sleep interrupted
         *
         *
         * 注意
         *      如果重写了 uncaughtException 方法, 线程中调用的逻辑不能使用 try{}catch{}, 会导致 uncaughtException 方法不起效
         */
    }

    private static void testDemo01() {
        // 创建我的线程组
        ThreadGroup my = new ThreadGroup("我的线程组");
        MyThread07[] threads = new MyThread07[10];
        for (int i = 0; i < threads.length; i++) {
            MyThread07 thread = new MyThread07(my, "线程" + (i + 1), "1");
            threads[i] = thread;
            threads[i].start();
        }

        MyThread07 myThread07 = new MyThread07(my, "报错线程", "a");
        myThread07.start();

        /**{
         * 陷入了死循环中 : 线程1
         * 陷入了死循环中 : 线程3
         * 陷入了死循环中 : 线程2
         * 陷入了死循环中 : 线程4
         * 陷入了死循环中 : 线程5
         * 陷入了死循环中 : 线程6
         * 陷入了死循环中 : 线程7
         * 陷入了死循环中 : 线程8
         * 陷入了死循环中 : 线程9
         * 陷入了死循环中 : 线程10
         * Exception in thread "报错线程" java.lang.NumberFormatException: For input string: "a"
         * 	at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
         * 	at java.lang.Integer.parseInt(Integer.java:580)
         * 	at java.lang.Integer.parseInt(Integer.java:615)
         * 	at com.beikai.springboottestdemo.Thread.AboutBook.chapter7_1.MyThread07.run(ExceptionOfThreadGroupDemo01.java:43)
         * 陷入了死循环中 : 线程5
         * 陷入了死循环中 : 线程6
         * 陷入了死循环中 : 线程9
         *
         * 从上面打印可以看出,如果线程组中一个线程有异常,会抛出异常,但是不会影响到线程组中其他的线程,其他线程继续运行
         */}
}

class MyThread07 extends Thread {

    private String num;

    public MyThread07(ThreadGroup threadGroup, String name, String num) {
        super(threadGroup, name);
        this.num = num;
    }

    @SneakyThrows
    @Override
    public void run() {
        int i = Integer.parseInt(num);
        while (true) {
            System.out.println("陷入了死循环中 : " + Thread.currentThread().getName());
            Thread.sleep(1000);
        }
    }
}


class MyThread08 extends Thread {

    private String num;

    public MyThread08(ThreadGroup threadGroup, String name, String num) {
        super(threadGroup, name);
        this.num = num;
    }

    @SneakyThrows
    @Override
    public void run() {

        int i = Integer.parseInt(num);
        while (!this.isInterrupted()) {
            System.out.println("陷入了死循环中 : " + Thread.currentThread().getName());
            Thread.sleep(1000);
        }
    }
}

class MyThreadGroup extends ThreadGroup {

    public MyThreadGroup(String name) {
        super(name);
    }

    // 创建threadGroup 子类,重新 uncaughtException 方法
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        super.uncaughtException(t, e);
        // 如果有异常产生,调用线程组的 中断(interrupt) 方法,中断线程组中的所有线程
        this.interrupt();
    }
}