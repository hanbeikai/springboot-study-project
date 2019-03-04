package com.beikai.dubbo.controller;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.service.EchoService;
import com.beikai.dubbo.service.UserService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/4 20:41
 * @Version 1.0
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getuser",method = RequestMethod.POST)
    public Map<String, Object> user(@RequestParam String id) {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("user", userService.selectByPrimaryKey(Long.parseLong(id)));
            result.put("type", "200");
            result.put("content", "success");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("type", "500");
            result.put("content", e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/allUsers",method = RequestMethod.POST)
    public Map<String, Object> allUsers() {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("users", userService.selectAll());
            result.put("type", "200");
            result.put("content", "success");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("type", "500");
            result.put("content", e.getMessage());
        }
        return result;
    }

    /**
     * 回声测试
     * @return
     */
    @GetMapping("echoTest")
    public Map<String, Object> echoTest() {
        // 所有服务自动实现 EchoService 接口
        EchoService echoService = (EchoService) userService;
        // 回声测试可用性
        String status = (String) echoService.$echo("OK");
        Map<String, Object> result = Maps.newHashMap();
        result.put("status", status);
        return result;
    }

    /**
     * 获取上下文信息
     */
    @GetMapping("rpcContext")
    public Map<String, Object> rpcContext() {
        // 要先请求一次
        userService.selectAll();
        // 本端是否为消费端
        boolean isConsumerSide = RpcContext.getContext().isConsumerSide();
        // 获取最后一次调用的提供方IP地址
        String serverIp = RpcContext.getContext().getRemoteHost();
        // 获取当前服务配置信息，所有配置信息都将转换为URL的参数
        String application = RpcContext.getContext().getUrl().getParameter("application");

        Map<String, Object> result = Maps.newHashMap();
        result.put("isConsumerSide", isConsumerSide);
        result.put("serverIp", serverIp);
        result.put("application", application);

        return result;
    }

    private int count = 0;
    /**
     * 隐式参数
     *
     */
    @GetMapping("attachment")
    public Map<String, Object> attachment() {
        if (++count%2 != 0) {
            // 隐式传参，后面的远程调用都会隐式将这些参数发送到服务器端，类似cookie，用于框架集成，不建议常规业务使用
            RpcContext.getContext().setAttachment("count", count+"");
        }
        return userService.addMethod();
    }

}
