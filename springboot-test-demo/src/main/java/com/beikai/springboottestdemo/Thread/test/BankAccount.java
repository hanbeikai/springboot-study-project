package com.beikai.springboottestdemo.Thread.test;

/**
 * @ClassName BankAccount
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/7 15:12
 * @Version 1.0
 * 多线程 - 银行账户类
 *  使用 ReentrantLock 的方式
 **/
public interface BankAccount {
    /**
     * 定义一个取钱的方法
     */
    public void quQian(int money);
}
