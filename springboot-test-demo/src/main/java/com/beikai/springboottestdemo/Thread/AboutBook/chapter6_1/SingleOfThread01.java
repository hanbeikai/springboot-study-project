package com.beikai.springboottestdemo.Thread.AboutBook.chapter6_1;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/2
 * Time: 5:44 下午
 * Description: 单例模式 与 多线程的关系
 *
 *      立即加载 / 饿汉模式
 *
 */
public class SingleOfThread01 {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        MyThread myThread2 = new MyThread();
        myThread2.start();
        MyThread myThread3 = new MyThread();
        myThread3.start();

        /**{
         * 获取的值 都相同，说明获取到的是同一个对象
         * 值为 ： 413036867
         * 值为 ： 413036867
         * 值为 ： 413036867
         */
    }
}

class MyThread extends Thread{
    @Override
    public void run() {
        super.run();
        System.out.println("值为 ： " + SingleDemo.getInstance().hashCode());
    }
}

class SingleDemo{
    private static  SingleDemo singleDemo = new SingleDemo();

    public SingleDemo() {

    }

    public static SingleDemo getInstance(){
        // 此代码版本为立即加载
        // 这个版本的代码的缺点是不能有其他实例变量
        // 因为getInstance 没有同步，
        // 可能会导致 安全问题

        return singleDemo;
    }
}
