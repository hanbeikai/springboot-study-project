package com.beikai.springboottestdemo.Thread.ProductorAndCustomer;

import java.util.LinkedList;

/**
 * @ClassName MyStorage
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/7 18:40
 * @Version 1.0
 *
 *  生产者 消费者 之 仓库
 **/
public class MyStorage {

    /**
     * 定义一个容器,用于存放产品
     */
    private LinkedList<Object> linkedList = new LinkedList<>();

    /**
     * 定义容器的最大容量
     */
    public static final int STORAGE_COUNT = 10;

    /**
     * 生产者存进仓库的方法
     *  为了保证线程安全,设置为同步方法
     */
    public synchronized void addProductToStor(String product){

        // 为了保证仓库的容量在达到最大值的时候等待仓库中的数量减少再添加,此时,设置线程等待
        while (linkedList.size() >= STORAGE_COUNT){
            try {
                this.wait();
                System.out.println("仓库已经满了,请稍等再添加进仓库");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(Thread.currentThread().getName() + " 添加一个商品到仓库 : " + product);
        // 采用先进先出的原则, 所以新添加的商品放在后面  offer() 方法把对象存进后面
        linkedList.offer(product);

        // 添加商品到仓库后,通知消费者可以消费了
        this.notifyAll();
    }

    /**
     * 消费者从仓库中获取的方法
     *  为了保证线程安全,设置为同步方法
     */
    public synchronized void getProductFromStro(){

        // 防止当仓库中没有商品的情况出现,如果没有上面,消费者线程等待
        while (linkedList.size() <= 0){
            try {
                this.wait();
                System.out.println("仓库中已经没有商品了...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 采用先进先出的原则,所以消费的时候先消费先添加进来的  从最上面获取
        Object poll = linkedList.poll();
        System.out.println(Thread.currentThread().getName()+" 消费者消费了一个商品 : " +poll);

        // 消费完成后,通知生产者可以添加商品了
        this.notifyAll();

    }
}
