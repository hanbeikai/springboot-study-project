package com.beikai.springboottestdemo.writtentest.Thread;

/**
 * @ClassName PrintNum
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/7 22:07
 * @Version 1.0
 **/
public class PrintNum{

    private int num = 0;

    /**
     * 打印偶数
     */
    public synchronized void printOdd(){

        // 判断是否是奇数,如果是的话阻塞
        while (num % 2 != 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 如果不是奇数,打印
        System.out.println(Thread.currentThread().getName() + " : " + num);
        num ++;

        // 通知另一个线程
        this.notify();

    }

    /**
     * 打印奇数
     */
    public synchronized void printEven(){
        // 判断是否是奇数,如果不是,阻塞
        while (num % 2 == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        // 打印结果
        System.out.println(Thread.currentThread().getName() + " : " + num);
        num++;

        // 通知其他线程
        this.notify();

    }
}
