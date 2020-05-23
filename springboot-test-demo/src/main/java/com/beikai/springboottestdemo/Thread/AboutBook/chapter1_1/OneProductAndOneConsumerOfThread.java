package com.beikai.springboottestdemo.Thread.AboutBook.chapter1_1;

import lombok.SneakyThrows;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/23
 * Time: 6:06 下午
 * Description: 一生产者 一消费者  操作值
 */
public class OneProductAndOneConsumerOfThread {
    public static void main(String[] args) {
        Object lock = new Object();

        Producter producter = new Producter(lock);
        Consumer consumer = new Consumer(lock);

        Thread thread = new Thread(new ProductThread(producter));
        thread.start();

        Thread thread1 = new Thread(new ConsumerThread(consumer));
        thread1.start();

        /**{
         * 获取到的值是 : 1590229143160-162343318413516
         * 消费者在等待
         * 设置的值是 : 1590229143160-162343318427656
         * 生产者在等待
         * 获取到的值是 : 1590229143160-162343318427656
         * 消费者在等待
         * 设置的值是 : 1590229143160-162343318443254
         * 生产者在等待
         * 获取到的值是 : 1590229143160-162343318443254
         * 消费者在等待
         * 设置的值是 : 1590229143160-162343318457315
         * 生产者在等待
         * 获取到的值是 : 1590229143160-162343318457315
         * 消费者在等待
         * 设置的值是 : 1590229143160-162343318471022
         * 生产者在等待
         * 获取到的值是 : 1590229143160-162343318471022
         * 消费者在等待
         *
         *  可以看到 一个生产者生产 然后等待消费者消费
         */
    }
}

class ConsumerThread extends  Thread{
    private Consumer consumer;

    public ConsumerThread(Consumer consumer) {
        this.consumer = consumer;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true){
            consumer.consume();
        }
    }
}

class ProductThread extends Thread{
    private Producter producter;

    public ProductThread(Producter producter) {
        this.producter = producter;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true){
            producter.product();
        }
    }
}

class ValueClass{
    public static String value = "";
}


class Producter{
    private Object lock;

    public Producter(Object lock) {
        super();
        this.lock = lock;
    }

    public void product() throws InterruptedException {
        synchronized (lock){
            // 判断公有制是否有变化
            if (!ValueClass.value.equals("")) {
                System.out.println("生产者在等待");
                lock.wait();
            }

            ValueClass.value = System.currentTimeMillis() + "-" + System.nanoTime();
            System.out.println("设置的值是 : " + ValueClass.value);
            // 通知生产者
            lock.notify();
        }
    }
}

class Consumer{
    private Object lock;

    public Consumer(Object lock) {
        super();
        this.lock = lock;
    }

    public void consume() throws InterruptedException {
        synchronized (lock){
            // 判断公有值是否有变
            if (ValueClass.value.equals("")) {
                System.out.println("消费者在等待");
                lock.wait();
            }

            System.out.println("获取到的值是 : " + ValueClass.value);
            ValueClass.value = "";
            // 通知生产者
            lock.notify();
        }
    }
}
