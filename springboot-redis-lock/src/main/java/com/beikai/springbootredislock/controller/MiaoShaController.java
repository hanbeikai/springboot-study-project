package com.beikai.springbootredislock.controller;

import com.beikai.springbootredislock.service.MiaoShaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
    public Object getUserInfo(@RequestParam String goodsCode,@RequestParam String userId){
        // http请求,后台就是一个thread线程,去调用service方法
        return miaoShaService.miaosha(goodsCode,userId);
    }
}
