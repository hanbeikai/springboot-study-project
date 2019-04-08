package com.beikai.springboottestdemo.Thread.test;

/**
 * @ClassName BankAccount
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/7 15:12
 * @Version 1.0
 * 多线程 - 银行账户类
 *  使用同步代码块的方式
 **/
public class BankAccount2 implements BankAccount{
    /**
     * 定义账户余额  万元
     */
    private int account = 10000;

    /**
     * 定义一个常量对象 作为锁对象
     */
    private static final Object obj = new Object();
    /**
     * 定义一个取钱的方法
     */
    @Override
    public void quQian(int money) {
        // 加锁
        synchronized (obj){
            try {
                System.out.println("取钱前" + Thread.currentThread().getName() + "查询账户余额 : " + account + "万元");
                account -= money;
                System.out.println("取钱后" + Thread.currentThread().getName() + "查询账户余额 : " + account + "万元");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
