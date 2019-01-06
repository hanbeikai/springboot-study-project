package com.beikai.springbootthread.test;

import com.alibaba.fastjson.JSON;
import com.beikai.springbootthread.service.IThreadService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

}
