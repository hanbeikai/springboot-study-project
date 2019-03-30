package com.beikai.controller;

import com.beikai.vo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 用户登录控制类
 */
@Controller
public class UserController {
    @RequestMapping(value = "/subLogin",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String subLogin(User user){
        // 获取主体
        Subject subject = SecurityUtils.getSubject();
        // 配置token
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        // 验证登录

        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            return e.getMessage();
        }

        if (subject.hasRole("admin")){
            return "有admin权限";
        }

        return "无admin权限";
    }

    /**
     * 使用编程的方式配置角色 来限定访问该方法的角色
     * @return
     */
    @RequestMapping(value = "/testrole",method = RequestMethod.GET)
    @ResponseBody
    public String testrole(){
        return "testrole success";
    }

    /**
     * 使用编程的方式配置角色 来限定访问该方法的角色
     * @return
     */
    @RequestMapping(value = "/testrole1",method = RequestMethod.GET)
    @ResponseBody
    public String testrole1(){
        return "testrole1 success";
    }

    /**
     * 使用编程的方式配置权限 来限定访问该方法的权限
     * @return
     */
    @RequestMapping(value = "/testpermission",method = RequestMethod.GET)
    @ResponseBody
    public String testpermission(){
        return "testpermission success";
    }

    /**
     * 使用编程的方式配置权限 来限定访问该方法的权限
     * @return
     */
    @RequestMapping(value = "/testpermission1",method = RequestMethod.GET)
    @ResponseBody
    public String testpermission1(){
        return "testpermission1 success";
    }

}
