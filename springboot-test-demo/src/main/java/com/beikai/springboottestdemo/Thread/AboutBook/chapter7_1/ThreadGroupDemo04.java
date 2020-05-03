package com.beikai.springboottestdemo.Thread.AboutBook.chapter7_1;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/3
 * Time: 4:23 下午
 * Description: 获取线程组 的根线程组
 *  如果是main主线程,父线程组名字是 system
 *  再网上是 空了
 */
public class ThreadGroupDemo04 {

    public static void main(String[] args) {
        System.out.println("当前线程名 : " + Thread.currentThread().getName() +
                " 所在的线程组名 : " + Thread.currentThread().getThreadGroup().getParent().getName());

        System.out.println(
                " 所在的线父线程组的父线程组名 : " + Thread.currentThread().getThreadGroup().getParent().getParent().getName());

        System.out.println(
                " 所在的线程组父线程组父线程组的父线程组名 : " + Thread.currentThread().getThreadGroup().getParent().getParent().getParent().getName());
    }
}
