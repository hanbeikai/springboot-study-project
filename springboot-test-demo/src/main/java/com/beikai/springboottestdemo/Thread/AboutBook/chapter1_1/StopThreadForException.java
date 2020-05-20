package com.beikai.springboottestdemo.Thread.AboutBook.chapter1_1;

import lombok.SneakyThrows;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/20
 * Time: 8:44 上午
 * Description: 通过异常的方式停止线程
 */
public class StopThreadForException {

    public static void main(String[] args) throws InterruptedException {
        Mythread02 mythread02 = new Mythread02();
        mythread02.start();
        Thread.sleep(1000);
        mythread02.interrupt();

        /**{
         * i = 374342
         * i = 374343
         * i = 374344
         * i = 374345
         * 已经是停止状态了...
         * Exception in thread "Thread-0" java.lang.InterruptedException
         * 	at com.beikai.springboottestdemo.Thread.AboutBook.chapter1_1.StopThreadForException$Mythread02.run(StopThreadForException.java:32)
         */
    }


    private static class Mythread02 extends Thread{
        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < 10000000; i++){
                if (this.isInterrupted()) {
                    System.out.println("已经是停止状态了... ");
                    throw new InterruptedException();
                }
                System.out.println("i = " + i);
            }
            System.out.println("我在 循环下面  ");
        }
    }
}
