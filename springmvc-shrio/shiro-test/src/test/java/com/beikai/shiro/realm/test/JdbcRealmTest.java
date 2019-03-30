package com.beikai.shiro.realm.test;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * jdbc realm 测试  这个需要连接数据库
 */
public class JdbcRealmTest {

    // 创建数据库连接资源
    DruidDataSource dataSource = new DruidDataSource();
    // 创建连接参数
    {
        dataSource.setUrl("jdbc:mysql://localhost:3306/shirotest");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
    }

    @Test
    public void testAutentication(){
        // 构建jdbcrealm
        JdbcRealm jdbcRealm = new JdbcRealm();
        // 设置连接资源
        jdbcRealm.setDataSource(dataSource);
        // 设置查询权限的开关(默认是 false)
        jdbcRealm.setPermissionsLookupEnabled(true);

        // 如果不想使用shiro默认的sql 语句  可以自己写
        String sql = "select password from test_user where username = ?";
        // 验证用户登录信息
        jdbcRealm.setAuthenticationQuery(sql);

        // 验证用户角色
        String roleSql = "select roles from test_user_roles where user_name = ?";
        jdbcRealm.setUserRolesQuery(roleSql);

        // 验证用户权限
        String permissionsSql = "select permission from test_roles_permissions where role_name = ?";
        jdbcRealm.setPermissionsQuery(permissionsSql);

        // 1 构建 securitymanager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        // 1.1 设置reaml
        defaultSecurityManager.setRealm(jdbcRealm );
        // 2 构建认证主体
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        // 2.1 构建验证token
        UsernamePasswordToken token = new UsernamePasswordToken("admin1","admin");
        subject.login(token);
        // 2.2 验证是否已经登录
        System.out.println("是否已经登录 : " + subject.isAuthenticated());

        subject.checkRoles("admin");
        System.out.println("已经验证用户角色信息");

        subject.checkPermission("user:select");
        System.out.println("已经验证用户角色权限");

    }
}
