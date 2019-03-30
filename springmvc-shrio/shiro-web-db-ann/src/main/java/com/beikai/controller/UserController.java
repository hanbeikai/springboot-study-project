package com.beikai.controller;

import com.beikai.vo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
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
     * @RequiresRoles("admin") 意思当前角色是admin才能访问这个方法
     * @return
     */
    @RequiresRoles("admin")
    @RequestMapping(value = "/testrole",method = RequestMethod.GET)
    @ResponseBody
    public String testrole(){
        return "testrole success";
    }

    /**
     * @RequiresRoles("admin") 意思当前角色是admin才能访问这个方法  括号中的角色可以多个 用逗号隔开
     * @return
     */
    @RequiresRoles("admin1")
    @RequestMapping(value = "/testrole1",method = RequestMethod.GET)
    @ResponseBody
    public String testrole1(){
        return "testrole1 success";
    }

    /**
     * @RequiresPermissions("user:select") 意思是当前角色具有 user:select 权限才能访问这个方法
     * @return
     */
    @RequiresPermissions("user:select")
    @RequestMapping(value = "/testpermission",method = RequestMethod.GET)
    @ResponseBody
    public String testpermission(){
        return "testpermission success";
    }

    /**
     * @RequiresPermissions("user:select") 意思是当前角色具有 user:select 权限才能访问这个方法
     * @return
     */
    @RequiresPermissions("user:delete")
    @RequestMapping(value = "/testpermission1",method = RequestMethod.GET)
    @ResponseBody
    public String testpermission1(){
        return "testpermission1 success";
    }

}
