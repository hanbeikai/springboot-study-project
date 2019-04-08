package com.beikai.springboottestdemo.Thread.ProductorAndCustomer;

/**
 * @ClassName Customer
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/7 21:50
 * @Version 1.0
 *
 * 消费者
 **/
public class Customer extends Thread{

    /**
     * 注入仓库
     */
    private MyStorage myStorage;

    /**
     * 构造器注入的方式
     */
    public Customer(MyStorage myStorage){
        super();
        this.myStorage = myStorage;
    }

    /**
     * 重写run方法
     */
    @Override
    public void run() {
        // 获取仓库中的产品
        for (int i = 0; i < 30; i++) {
            myStorage.getProductFromStro();
        }
    }
}
