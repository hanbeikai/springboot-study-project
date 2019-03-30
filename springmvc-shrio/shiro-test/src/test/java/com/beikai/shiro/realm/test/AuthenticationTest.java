package com.beikai.shiro.realm.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 *  认证测试类
 */
public class AuthenticationTest {

    // 指定 realm
    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before  // 认证之前进行的操作的注解
    // 使用realm 把账户密码添加到数据库中   添加角色
    // 添加账户的方法 参数是 : 用户名  用户密码  角色(角色可以使多个)
    public void addUser(){
        simpleAccountRealm.addAccount("admin","admin","admin","user");
    }

    @Test
    // 测试认证方法
    public void testAuthentication(){
        // 1.构建securitymanager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        // 1.2 securitymanager 环境中 添加 realm
        defaultSecurityManager.setRealm(simpleAccountRealm);

        // 2. 主体提交认证请求
        // 2.1 设置securityutils环境
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        // 2.2 获取主体情况
        Subject subject = SecurityUtils.getSubject();
        // 2.3 存认证数据
        UsernamePasswordToken token = new UsernamePasswordToken("admin","admin");
        // 2.4  提交认证请求
        subject.login(token);
        // 判断是否认证
        System.out.println("isAuthenticated(是否认证) :" + subject.isAuthenticated());

        /*// 2.5 退出登录
        subject.logout();
        // 判断是否认证
        System.out.println("isAuthenticated(是否认证) :" + subject.isAuthenticated());*/

        // 3. 验证权限
        subject.checkRoles("admin","user");


    }
}
