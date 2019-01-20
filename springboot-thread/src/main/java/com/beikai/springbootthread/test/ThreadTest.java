package com.beikai.springbootthread.test;

import com.alibaba.fastjson.JSON;
import com.beikai.springbootthread.service.CreateIdService;
import com.beikai.springbootthread.service.IThreadService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName ThreadTest
 * @Description TODO
 * @Author Admin
 * @Date 2019/1/6 22:22
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadTest {

    @Autowired
    private IThreadService iThreadService;
    /**
     * 测试 Runnable 线程
     */
    @Test
    public void testRunnable(){
        long l = System.currentTimeMillis();

        Object userInfoByRunnable = iThreadService.getUserInfoByRunnable();

        System.out.println("时间是 : " + (System.currentTimeMillis() - l));

        System.out.println("获取的内容是 : " + JSON.parseObject(JSON.toJSONString(userInfoByRunnable)));
    }

    /**
     * 测试 Callable 线程
     */
    @Test
    public void testCallable() throws Exception {

        long l = System.currentTimeMillis();

        Object userInfoByRunnable = iThreadService.getUserInfoByCallable();

        System.out.println("获取的内容是 : " + JSON.parseObject(JSON.toJSONString(userInfoByRunnable)) + "   时间是 : " + (System.currentTimeMillis() - l));
    }

    /**
     * 测试uuid的获取
     *  Qualifier 指定实现类
     */
    @Autowired
    //@Qualifier("uuidServiceImpl")
    @Qualifier("RedisServiceImpl")
    private CreateIdService createIdService;

    /**
     * 设置线程数
     */
    private static final int THREADNUM = 1000;

    /**
     * 创建线程同步工具
     */
    private static CountDownLatch countDownLatch = new CountDownLatch(THREADNUM);

    /**
     * 创建测试方法
     */
    @Test
    public void test() throws InterruptedException {
        System.out.println("-----------------线程开始------------------");
        long l = System.currentTimeMillis();
        for (int i = 0; i < THREADNUM; i++) {

            new Thread(new testThread()).start();

            // 对线程进行计数, threadnum 为 -1 时,唤醒所有线程
            countDownLatch.countDown();
        }
        Thread.currentThread();
        Thread.sleep(3000);

        System.out.println("--------------------所有线程结束--------------------");
        System.out.println("用时 : " + (System.currentTimeMillis() - l));
    }

    /**
     * 创建线程类
     */
    class testThread implements Runnable{
        @Override
        public void run() {

            try {
                // 等待线程同步执行
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            createIdService.getId();
        }
    }
}
