package com.beikai.databasetest.test;

import com.beikai.databasetest.Service.SCService;
import com.beikai.databasetest.model.SC;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SCServiceImplTest {

    @Autowired
    private SCService scService;

    @Test
    public void insert() throws InterruptedException {
        long l = System.currentTimeMillis();

        for (int j = 0; j < 200; j++) {
            ExecutorService executorService = Executors.newFixedThreadPool(500);
            for (int i = 0; i < 3500; i++) {
                final int index = i;
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        SC sc = new SC();
                        int courseid = (int) (Math.random() * 100) + 1 + 400;
                        int studentid = (int) (Math.random() * 70000) + 1 + 70000;
                        int score = (int) (Math.random() * 100) + 1;
                        sc.setcId(courseid);
                        sc.setsId(studentid);
                        sc.setScore(score);
                        scService.insert(sc);
                        System.out.println("当前线程是 : " + Thread.currentThread().getName() + "-"+ index);
                    }
                });
            }
            executorService.shutdown();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        }
        System.out.println("創建結束了, 用时 ： " + (System.currentTimeMillis() - l));
    }


    @Test
    public void insertSelective() {
    }
}