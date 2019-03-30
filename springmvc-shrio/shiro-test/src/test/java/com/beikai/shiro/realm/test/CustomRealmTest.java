package com.beikai.shiro.realm.test;

import com.beikai.shrio.realm.CustomRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * 使用自定义realm
 */
public class CustomRealmTest {
    @Test
    public void testAutentication(){
        CustomRealm customRealm = new CustomRealm();

        // 1 构建 securitymanager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        // 1.1 设置reaml
        defaultSecurityManager.setRealm(customRealm );

        // 3 对用户密码进行加密
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 3.1 设置加密算法
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        // 3.2 设置加密次数
        hashedCredentialsMatcher.setHashIterations(1);
        // 3.3 加密对象添加到realm中
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher);

        // 2 构建认证主体
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        // 2.1 构建验证token
        UsernamePasswordToken token = new UsernamePasswordToken("admin","1234567");
        subject.login(token);
        // 2.2 验证是否已经登录
        System.out.println("是否已经登录 : " + subject.isAuthenticated());

        subject.checkRoles("admin");
        System.out.println("已经验证用户角色信息");

        subject.checkPermission("user:select");
        System.out.println("已经验证用户角色权限");
    }
}
