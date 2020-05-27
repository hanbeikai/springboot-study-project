package com.beikai.springboottestdemo.Thread.AboutBook.chapter3_1;

import lombok.SneakyThrows;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/27
 * Time: 8:58 上午
 * Description: 验证 inheritableThreadLocal 从子类线程中获取父类线程继承的值
 */
public class InheritableThreadLocalOfThreadDemo01 {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            System.out.println("主线程中获取到的值是 : " + MyTools.myThreadLocal2.get());
            Thread.sleep(100);
        }

        Thread.sleep(5000);

        InitValue initValue = new InitValue();
        initValue.start();

        /**{
         * 主线程中获取到的值是 : 1590541332960
         * 主线程中获取到的值是 : 1590541332960
         * 主线程中获取到的值是 : 1590541332960
         * 主线程中获取到的值是 : 1590541332960
         * 主线程中获取到的值是 : 1590541332960
         * 在threadLocal线程线程中取到的值是 : 1590541332960
         * 在threadLocal线程线程中取到的值是 : 1590541332960
         * 在threadLocal线程线程中取到的值是 : 1590541332960
         * 在threadLocal线程线程中取到的值是 : 1590541332960
         * 在threadLocal线程线程中取到的值是 : 1590541332960
         *
         * 可以看出,使用了 继承 inheritableThreadLocal 的子类可以实现 子类线程获取父类线程的值的操作
         */
    }
}
class MyThreadLocal2 extends InheritableThreadLocal{
    @Override
    protected Object initialValue() {
        return System.currentTimeMillis();
    }
}

class MyTools {
    public static MyThreadLocal2 myThreadLocal2 = new MyThreadLocal2();
}

class InitValue extends Thread {

    @SneakyThrows
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("在threadLocal线程线程中取到的值是 : " + MyTools.myThreadLocal2.get());
            Thread.sleep(1000);

        }
    }
}