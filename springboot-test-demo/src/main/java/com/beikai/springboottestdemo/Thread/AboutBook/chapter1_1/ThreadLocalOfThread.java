package com.beikai.springboottestdemo.Thread.AboutBook.chapter1_1;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/25
 * Time: 8:57 上午
 * Description: No Description
 */
public class ThreadLocalOfThread {

    public static void main(String[] args) {
        ThreadLocalThread threadLocalThread = new ThreadLocalThread("a");
        ThreadLocalThread threadLocalThread1 = new ThreadLocalThread("b");
        threadLocalThread.start();
        threadLocalThread1.start();

        /**{
         * 线程 a 没有值
         * 线程 b 没有值
         * b
         * a
         *
         * 可以看出,不同线程中local的值是具有隔离性
         */
    }
}

class LocalDtools{
    public static ThreadLocal threadLocal = new ThreadLocal();
}

class ThreadLocalThread extends Thread{
    private String name;
    public ThreadLocalThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        if (LocalDtools.threadLocal.get() == null) {
            System.out.println("线程 " + name + " 没有值");
            LocalDtools.threadLocal.set(name);
        }

        Object o = LocalDtools.threadLocal.get();
        System.out.println(o);
    }
}