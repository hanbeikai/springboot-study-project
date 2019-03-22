package com.beikai.springbootredislock.test;

import com.beikai.springbootredislock.service.MiaoShaService;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName MiaoShaTest
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/21 22:37
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MiaoShaTest {

    long starttime = 0L;

    @Before
    public void start(){
        log.info("---------开始-----------");
    }

    @After
    public void end(){
        log.info("----------结束--------所用时长是 : " + (System.currentTimeMillis() - starttime));
    }

    @Autowired
    private MiaoShaService miaoShaService;


    @Test
    public void banchmark() throws Exception{

        starttime = System.currentTimeMillis();

        // 模拟的请求数量
        int threadNum = 500;

        // 倒计数器, 用于模拟高并发(信号枪机制)
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        // 创建n个线程
        Thread[] threads = new Thread[threadNum];
        for (int i = 0; i < threadNum; i++) {
            String userId = "tony";

            Thread thread = new Thread(() -> {
                try {

                    // 等待 countDownLatch 的值为0 在其他线程都就绪后,执行后续的代码
                    countDownLatch.await();

                    // http请求实际上是多线程调用这个方法
                    Object bike = miaoShaService.miaosha("bike", userId);

                }catch (Exception e){
                    e.printStackTrace();
                }
            });

            threads[i] = thread;
            thread.start();

            // 倒计时 减一
            countDownLatch.countDown();
        }
    }

}
