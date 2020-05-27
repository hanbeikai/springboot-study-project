package com.beikai.springboottestdemo.Thread.AboutBook.chapter1_1;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/27
 * Time: 8:42 上午
 * Description: 设置threadLocal初始化值
 */
public class ThreadLocalInitialValueOfThreadDemo01 {

    public static MyThreadLocal myThreadLocal = new MyThreadLocal();
    public static void main(String[] args) {
        if (myThreadLocal.get() == null) {
            System.out.println("初始化未成功,重新设置值");
            myThreadLocal.set("值");
        }

        System.out.println(myThreadLocal.get());
        System.out.println(myThreadLocal.get());

        /**{
         * 默认值
         * 默认值
         *
         *  从打印可以看出,实现了默认值
         */
    }
}


class MyThreadLocal extends ThreadLocal{
    @Override
    protected Object initialValue() {
        return "默认值";
    }
}
