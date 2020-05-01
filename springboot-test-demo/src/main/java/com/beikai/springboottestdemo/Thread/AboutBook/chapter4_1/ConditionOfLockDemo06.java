package com.beikai.springboottestdemo.Thread.AboutBook.chapter4_1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/1
 * Time: 8:13 下午
 * Description: condition  可以使用condition 对象对程序执行的业务进行排序
 */
public class ConditionOfLockDemo06 {
    volatile public static int flag = 1;
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition conditionA = lock.newCondition();
    private static Condition conditionB = lock.newCondition();
    private static Condition conditionC = lock.newCondition();

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            lock.lock();
            try {

                // 判断flag 是否未指定值 轮询
                while (flag != 1) {
                    conditionA.await();
                }

                // 打印
                for (int i = 0; i < 3; i++) {
                    System.out.println("thread-name ： " + Thread.currentThread().getName() + (i + 1));

                }

                // 设置 flag 参数未 2
                flag = 2;

                // 通知所有人
                conditionA.signalAll();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread threadB = new Thread(() -> {
            lock.lock();
            try {

                // 判断flag 是否未指定值 轮询
                while (flag != 2) {
                    conditionB.await();
                }

                // 打印
                for (int i = 0; i < 3; i++) {
                    System.out.println("thread-name ： " + Thread.currentThread().getName() + (i + 1));

                }

                // 设置 flag 参数未 2
                flag = 3;

                // 通知所有人
                conditionB.signalAll();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread threadC = new Thread(() -> {
            lock.lock();
            try {

                // 判断flag 是否未指定值 轮询
                while (flag != 3) {
                    conditionC.await();
                }

                // 打印
                for (int i = 0; i < 3; i++) {
                    System.out.println("thread-name ： " + Thread.currentThread().getName() + (i + 1));

                }

                // 设置 flag 参数未 2
                flag = 1;

                // 通知所有人
                conditionC.signalAll();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });


        Thread[] threadas = new Thread[5];
        Thread[] threadbs = new Thread[5];
        Thread[] threadcs = new Thread[5];

        for (int i = 0; i < 5; i++) {

            threadas[i] = new Thread(threadA);
            threadbs[i] = new Thread(threadB);
            threadcs[i] = new Thread(threadC);

            threadas[i].setName("thread-a-" + i + "-");
            threadas[i].start();
            threadbs[i].setName("thread-b-" + i + "-");
            threadbs[i].start();
            threadcs[i].setName("thread-c-" + i + "-");
            threadcs[i].start();
        }

        /**
         * thread-name ： thread-a-0-1
         * thread-name ： thread-a-0-2
         * thread-name ： thread-a-0-3
         * thread-name ： thread-b-0-1
         * thread-name ： thread-b-0-2
         * thread-name ： thread-b-0-3
         * thread-name ： thread-c-0-1
         * thread-name ： thread-c-0-2
         * thread-name ： thread-c-0-3
         * thread-name ： thread-a-1-1
         * thread-name ： thread-a-1-2
         * thread-name ： thread-a-1-3
         * thread-name ： thread-b-4-1
         * thread-name ： thread-b-4-2
         * thread-name ： thread-b-4-3
         * thread-name ： thread-c-1-1
         * thread-name ： thread-c-1-2
         * thread-name ： thread-c-1-3
         * thread-name ： thread-a-3-1
         * thread-name ： thread-a-3-2
         * thread-name ： thread-a-3-3
         * thread-name ： thread-b-3-1
         * thread-name ： thread-b-3-2
         * thread-name ： thread-b-3-3
         * thread-name ： thread-c-4-1
         * thread-name ： thread-c-4-2
         * thread-name ： thread-c-4-3
         */

    }
}
