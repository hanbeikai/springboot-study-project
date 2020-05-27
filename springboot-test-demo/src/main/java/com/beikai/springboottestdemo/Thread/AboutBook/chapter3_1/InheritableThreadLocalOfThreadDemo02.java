package com.beikai.springboottestdemo.Thread.AboutBook.chapter3_1;

import lombok.SneakyThrows;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/27
 * Time: 8:58 上午
 * Description: 验证 inheritableThreadLocal 从子类线程中获取父类线程继承的值
 */
public class InheritableThreadLocalOfThreadDemo02 {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            System.out.println("主线程中获取到的值是 : " + MyTools2.myThreadLocal2.get());
            Thread.sleep(100);
        }

        Thread.sleep(5000);

        InitValue1 initValue = new InitValue1();
        initValue.start();

        Thread.sleep(5000);

        for (int i = 0; i < 5; i++) {
            System.out.println("主线程中获取到的值是 : " + MyTools2.myThreadLocal2.get());
            Thread.sleep(100);
        }

        /**{
         * 主线程中获取到的值是 : 1590541725952
         * 主线程中获取到的值是 : 1590541725952
         * 主线程中获取到的值是 : 1590541725952
         * 主线程中获取到的值是 : 1590541725952
         * 主线程中获取到的值是 : 1590541725952
         * 在threadLocal线程线程中取到的值是 : 1590541725952  在子线后加的
         * 在threadLocal线程线程中取到的值是 : 1590541725952  在子线后加的
         * 在threadLocal线程线程中取到的值是 : 1590541725952  在子线后加的
         * 在threadLocal线程线程中取到的值是 : 1590541725952  在子线后加的
         * 在threadLocal线程线程中取到的值是 : 1590541725952  在子线后加的
         * 主线程中获取到的值是 : 1590541725952
         * 主线程中获取到的值是 : 1590541725952
         * 主线程中获取到的值是 : 1590541725952
         * 主线程中获取到的值是 : 1590541725952
         * 主线程中获取到的值是 : 1590541725952
         *
         * 可以看出,使用了 继承 inheritableThreadLocal 的子类可以实现 子类线程获取父类线程的值的操作,
         * 子线程也可以对父线程的值进行修改,则子线程获取到的值是修改后的值,但是主线程获取到的还是原来的值
         *
         * 注意: 如果子线程在获取到值的同时,父线程修改了值,则子线程获取到的还是旧值
         */
    }
}

class MyThreadLocal3 extends InheritableThreadLocal{
    @Override
    protected Object initialValue() {
        return System.currentTimeMillis();
    }

    @Override
    protected Object childValue(Object parentValue) {
        return parentValue + "  在子线后加的";
    }
}

class MyTools2 {
    public static MyThreadLocal3 myThreadLocal2 = new MyThreadLocal3();
}

class InitValue1 extends Thread {

    @SneakyThrows
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("在threadLocal线程线程中取到的值是 : " + MyTools2.myThreadLocal2.get());
            Thread.sleep(1000);

        }
    }
}