package com.beikai.springboottestdemo.Thread.ProductorAndCustomer;

import java.util.Random;

/**
 * @ClassName Productor
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/7 21:46
 * @Version 1.0
 *
 *  生产者
 **/
public class Productor extends Thread{

    /**
     * 注入仓库
     */
    private MyStorage myStorage;

    /**
     * 构造器注入的方式
     */
    public Productor(MyStorage myStorage){
        super();
        this.myStorage = myStorage;
    }

    /**
     * 重写run方法
     */
    @Override
    public void run() {
        // 存入仓库商品
        for (int i = 0; i < 30; i++) {
            String product = "产品编号 : " + new Random().nextInt(100);
            myStorage.addProductToStor(product);
        }
    }
}
