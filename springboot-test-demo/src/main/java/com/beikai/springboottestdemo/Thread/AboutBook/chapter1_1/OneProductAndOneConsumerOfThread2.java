package com.beikai.springboottestdemo.Thread.AboutBook.chapter1_1;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/23
 * Time: 6:33 下午
 * Description: 一生产者 一消费者 操作栈
 */
public class OneProductAndOneConsumerOfThread2 {

    public static void main(String[] args) {
        MyStack myStack = new MyStack();

        ProductOfStack productOfStack = new ProductOfStack(myStack);
        ConsumerOfStack consumerOfStack = new ConsumerOfStack(myStack);

        // 创建线程
        Thread thread = new Thread(new ProductOfStackThread(productOfStack));
        thread.start();

        Thread thread1 = new Thread(new ConsumerOfStackThread(consumerOfStack));
        thread1.start();

        /**{
         * 生产者等待
         * 移除后集合的长度为 : 0
         * 消费者等待
         * 添加的值是 : 0.8534182340940694
         * 添加参数后集合的长度为 : 1
         * 生产者等待
         * 移除后集合的长度为 : 0
         * 消费者等待
         * 添加的值是 : 0.7862525011219215
         * 添加参数后集合的长度为 : 1
         * 生产者等待
         * 移除后集合的长度为 : 0
         * 消费者等待
         * 添加的值是 : 0.12932308525871827
         * 添加参数后集合的长度为 : 1
         * 生产者等待
         * 移除后集合的长度为 : 0
         * 消费者等待
         * 添加的值是 : 0.17625480819882866
         * 添加参数后集合的长度为 : 1
         * 生产者等待
         * 移除后集合的长度为 : 0
         * 消费者等待
         * 添加的值是 : 0.5691768781174613
         * 添加参数后集合的长度为 : 1
         * 生产者等待
         * 移除后集合的长度为 : 0
         * 消费者等待
         * 添加的值是 : 0.105624224200626
         * 添加参数后集合的长度为 : 1
         *
         *  从上可以看出 一个生产者添加到集合后, 会等待消费者消费,消费者消费完后会等待生产者生产
         */
    }
}

class ProductOfStackThread extends Thread{
    private ProductOfStack productOfStack;

    public ProductOfStackThread(ProductOfStack productOfStack) {
        this.productOfStack = productOfStack;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true){
            productOfStack.pushService();
        }

    }
}
class ConsumerOfStackThread extends Thread{
    private ConsumerOfStack consumerOfStack;

    public ConsumerOfStackThread(ConsumerOfStack consumerOfStack) {
        this.consumerOfStack = consumerOfStack;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true){
            consumerOfStack.popService();
        }

    }
}

class ProductOfStack {

    private MyStack myStack;

    public ProductOfStack(MyStack myStack) {
        this.myStack = myStack;
    }

    public void pushService() throws InterruptedException {
        myStack.push();
    }
}
class ConsumerOfStack {

    private MyStack myStack;

    public ConsumerOfStack(MyStack myStack) {
        this.myStack = myStack;
    }

    public void popService() throws InterruptedException {
        myStack.pop();
    }
}


class MyStack {

    private List<String> list = new ArrayList<>();

    // 添加 参数
    synchronized public void push() throws InterruptedException {
        if (list.size() != 0) {
            System.out.println("生产者等待 ");
            this.wait();
        }

        // 此时集合是空的 添加参数
        double random = Math.random();
        list.add(random + "");
        System.out.println("添加的值是 : " + random);
        // 通知其他线程
        this.notify();
        System.out.println("添加参数后集合的长度为 : " + list.size());
    }

    // 移除 参数
    synchronized public void pop() throws InterruptedException{
        if (list.size() == 0) {
            System.out.println("消费者等待");
            this.wait();
        }

        // 此时集合不是空的 移除参数
        String s = list.get(0);
        list.remove(0);
        // 通知其线程
        this.notify();
        System.out.println("移除后集合的长度为 : " + list.size());
    }
}
