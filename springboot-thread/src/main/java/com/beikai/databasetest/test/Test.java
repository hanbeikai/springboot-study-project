package com.beikai.databasetest.test;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * @ClassName Test
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/17 2:21
 * @Version 1.0
 **/
public class Test {

    public static void main(String[] args){
        ExecutorService fixedThreadPool = newFixedThreadPool(5);
        for (int i = 0; i < 10000; i++) {
            final int index = i;
            fixedThreadPool.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(index + "    " + Thread.currentThread().getName());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        }
        fixedThreadPool.shutdown();
}}
