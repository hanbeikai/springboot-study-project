package com.beikai.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {

    // 存用户信息的map
    Map<String,String> userMap = new HashMap<String, String>();
    {
        userMap.put("admin","0287040c474dbf44cdeb17eebb99d828");
        // 设置 名字
        super.setName("CustomRealm");
    }

    /**
     * 这个方法是用来做授权的
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 通过 getPrimaryPrincipal() 方法获取用户名
        String username = (String) principalCollection.getPrimaryPrincipal();
        // 通过用户名获取角色信息
        Set<String> rolesSet = getRoleByUsername(username);
        // 通过用户名获取权限信息
        Set<String> permissions = getPermissionByUsername(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 授权器设置 权限对象
        simpleAuthorizationInfo.setStringPermissions(permissions);
        // 授权器设置 角色对象
        simpleAuthorizationInfo.setRoles(rolesSet);
        // 返回授权结果
        return simpleAuthorizationInfo;
    }

    /**
     * 模拟数据库通过用户名获取权限信息
     * @param username
     * @return
     */
    private Set<String> getPermissionByUsername(String username) {
        Set<String> set = new HashSet<String>();
        set.add("user:select");
        set.add("user:delete");
        return set;
    }

    /**
     * 模拟数据库获取角色信息
     * @param username
     * @return
     */
    private Set<String> getRoleByUsername(String username) {
        Set<String> set = new HashSet<String>();
        set.add("admin");
        set.add("user");
        return set;
    }

    /**
     *   这个方法是用来做认证的
     *   AuthenticationToken  传过来的认证信息
      */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 1. 从主体传过来的认证信息中 获取用户民
        String username = (String) authenticationToken.getPrincipal();
        // 2. 通过用户名到数据库中获取凭证
        String password = getPasswordByUsername(username);

        if (password == null || password == ""){
            return null;
        }
        // 创建构造器  设置认证对象
        //SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo("admin",password,"CustomRealm");
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username,password,this.getName());

        // 如果采用了加盐的方式 在此位置把盐输进去
        simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("admin"));

        return simpleAuthenticationInfo;
    }

    // 通过用户名获取用户密码的方法  (可以通过这个方法连接数据库或其他方式)
    private String getPasswordByUsername(String username) {
        // 使用map的形式 模拟数据库
        return userMap.get(username);
    }

    // 使用MD5对密码进行加密
    public static void main(String[] args) {
        // 使用md5 进行加密  如果使用加盐的形式  在后面添加一个字符串用做盐
        Md5Hash md5Hash = new Md5Hash("1234567","admin");
        System.out.println("使用md5加密后的结果是 : " + md5Hash.toString());

    }
}
