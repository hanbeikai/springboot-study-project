package com.beikai.RedisLock.controller;

import com.beikai.RedisLock.service.MiaoShaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName MiaoShaController
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/21 21:53
 * @Version 1.0
 **/
@RestController
@RequestMapping("/miaosha")
@Slf4j
public class MiaoShaController {

    @Autowired
    private MiaoShaService miaoShaService;

    /**
     * 跳转到首页
     */
    @RequestMapping("/")
    public String index(ModelMap model, HttpServletRequest request){
        log.info("当前服务窗口 : " + request.getLocalAddr() + " : " + request.getLocalPort());
        String message = "当前服务窗口 : " + request.getLocalAddr() + " : " + request.getLocalPort();
        model.put("message",message);
        return "home";
    }

    /**
     * 秒杀接口
     */
    @RequestMapping(value = "/miaosha",method = RequestMethod.GET)
    public Object getUserInfo(@RequestParam String goodsCode, @RequestParam String userId){
        // http请求,后台就是一个thread线程,去调用service方法
        return miaoShaService.miaosha(goodsCode,userId);
    }

    /**
     * 压力测试秒哈接口
     */
    @RequestMapping(value = "/miaoshaTest",method = RequestMethod.GET)
    public Object getUserInfo(@RequestParam String goodsCode){

        long starttime = System.currentTimeMillis();

        // 模拟的请求数量
        final int threadNum = 500;

        // 倒计数器, 用于模拟高并发(信号枪机制)
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        // 创建n个线程
        for (int i = 0; i < threadNum; i++) {
            String userId = "tony";

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // 等待 countDownLatch 的值为0 在其他线程都就绪后,执行后续的代码
                        countDownLatch.await();
                        // http请求实际上是多线程调用这个方法
                        miaoShaService.miaosha(goodsCode, userId);

                    }catch (Exception e){
                        log.error("出错 : ==== " + e);
                        e.printStackTrace();
                    }
                }
            }).start();

            Thread.currentThread();
            // 倒计时 减一
            countDownLatch.countDown();
        }

        System.out.println("用时 : --------------------------" +
                "--------------------------------------- " + (System.currentTimeMillis() - starttime));

        // http请求,后台就是一个thread线程,去调用service方法
        return "complete";
    }
}
