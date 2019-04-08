package com.beikai.springboottestdemo.Thread.test;

/**
 * @ClassName PersionThread
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/7 15:16
 * @Version 1.0
 *  用户线程对象
 **/
public class PersionThread extends Thread{

    /**
     * 声明一个银行账户对象
     */
    private BankAccount bankAccount;

    public PersionThread(BankAccount bankAccount) {
        super();
        this.bankAccount = bankAccount;
    }

    /**
     * 重写run方法
     */
    @Override
    public void run() {
        // 执行取钱方法
        bankAccount.quQian(1000);
    }
}
