package com.beikai.springboottestdemo.Thread.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName BankAccount
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/7 15:12
 * @Version 1.0
 * 多线程 - 银行账户类
 *  使用 ReentrantLock 的方式
 **/
public class BankAccount1 implements BankAccount{
    /**
     * 定义账户余额  万元
     */
    int account = 10000;

    Lock lock = new ReentrantLock();
    /**
     * 定义一个取钱的方法
     */
    @Override
    public void quQian(int money) {
        // 加锁
        lock.lock();
        try {
            System.out.println("取钱前" + Thread.currentThread().getName() + "查询账户余额 : " + account + "万元");
            account -= money;
            System.out.println("取钱后" + Thread.currentThread().getName() + "查询账户余额 : " + account + "万元");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 解锁
            lock.unlock();
        }

    }
}
