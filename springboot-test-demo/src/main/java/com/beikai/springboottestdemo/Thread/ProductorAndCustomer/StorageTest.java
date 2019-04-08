package com.beikai.springboottestdemo.Thread.ProductorAndCustomer;

/**
 * @ClassName StorageTest
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/7 19:00
 * @Version 1.0
 **/
public class StorageTest {
    public static void main(String[] args) {
        // 创建一个仓库
        MyStorage myStorage = new MyStorage();

        //创建三个生产者
        Productor zhangsan = new Productor(myStorage);
        Productor lisi = new Productor(myStorage);
        Productor chenqi = new Productor(myStorage);

        zhangsan.setName("zhangsan");
        lisi.setName("lisi");
        chenqi.setName("chenqi");

        zhangsan.start();
        lisi.start();
        chenqi.start();

        // 创建三个消费者
        Customer xiaoming = new Customer(myStorage);
        Customer xiaozhang = new Customer(myStorage);
        Customer xiaohua = new Customer(myStorage);

        xiaohua.setName("xiaohua");
        xiaozhang.setName("xiaozhang");
        xiaoming.setName("xiaoming");

        xiaohua.start();
        xiaozhang.start();
        xiaoming.start();
    }
}
