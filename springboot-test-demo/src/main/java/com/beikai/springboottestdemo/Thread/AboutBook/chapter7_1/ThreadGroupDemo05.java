package com.beikai.springboottestdemo.Thread.AboutBook.chapter7_1;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/3
 * Time: 4:46 下午
 * Description: 线程组 中 显示添加线程组
 */
public class ThreadGroupDemo05 {

    public static void main(String[] args) {
        System.out.println("当前线程组名称 : " + Thread.currentThread().getThreadGroup().getName());
        System.out.println("当前线程组中活动线程数量 : " + Thread.currentThread().getThreadGroup().activeCount());
        System.out.println("当前线程组中的活动线程组数量-前 : " + Thread.currentThread().getThreadGroup().activeGroupCount());
        ThreadGroup threadGroup = new ThreadGroup(Thread.currentThread().getThreadGroup(),"新线程组");
        System.out.println("当前线程组中的活动线程组数量-后 : " + Thread.currentThread().getThreadGroup().activeGroupCount() );
        System.out.println("父线程组名 : " + Thread.currentThread().getThreadGroup().getParent().getName());


        /**
         * 当前线程组名称 : main
         * 当前线程组中活动线程数量 : 2
         * 当前线程组中的活动线程组数量-前 : 0
         * 当前线程组中的活动线程组数量-后 : 1
         * 父线程组名 : system
         */

    }
}
