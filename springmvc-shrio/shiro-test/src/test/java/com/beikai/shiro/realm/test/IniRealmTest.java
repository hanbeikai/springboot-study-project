package com.beikai.shiro.realm.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * 使用 inirealm进行验证
 */
public class IniRealmTest {

    @Test
    public void testAuthentication(){
        // 创建 inirealm
        IniRealm iniRealm = new IniRealm ("classpath:user.ini");

        // 1 构建securitymanager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(iniRealm);
        // 2. 设置验证主体
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        // 2.1 构建验证信息
        UsernamePasswordToken token = new UsernamePasswordToken("admin","admin");
        subject.login(token);

        System.out.println(" 是否已经验证 : " + subject.isAuthenticated());

        // 3. 验证角色
        subject.checkRoles("admin");

        // 4. 验证权限
        subject.checkPermission("user:update");

    }
}
