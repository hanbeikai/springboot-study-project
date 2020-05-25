package com.beikai.springboottestdemo.Thread.AboutBook.chapter1_1;

import lombok.SneakyThrows;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/25
 * Time: 8:30 上午
 * Description: No Description
 */
public class JoinOfThread {

    public static void main(String[] args) throws InterruptedException {
        JoinThread joinThread = new JoinThread();
        joinThread.start();

        joinThread.join(2000);
        System.out.println("end   " + System.currentTimeMillis());
    }
}

class JoinThread extends Thread{
    @SneakyThrows
    @Override
    public void run() {
        System.out.println("begin " + System.currentTimeMillis());
        Thread.sleep(3000);
    }
}
