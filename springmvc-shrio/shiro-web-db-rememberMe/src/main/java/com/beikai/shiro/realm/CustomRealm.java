package com.beikai.shiro.realm;

import com.beikai.dao.UserDao;
import com.beikai.vo.User;
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

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {


    @Resource
    UserDao userDao;

    // 这个方法是用来做授权的
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
        // 从数据库中获取权限信息
        List<String> permissions = userDao.getPermissionByUsername(username);
        Set<String> set = new HashSet<String>(permissions);
        return set;
    }

    /**
     * 模拟数据库获取角色信息
     * @param username
     * @return
     */
    private Set<String> getRoleByUsername(String username) {
        System.out.println("customrealm---从数据库中获取角色信息");
        // 从数据库中获取用户角色
        List<String> roles = userDao.getRoleByUsername(username);
        Set<String> set = new HashSet<String>(roles);
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
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username,password,"CustomRealm");
        // 如果采用了加盐的方式 在此位置把盐输进去
        simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(username));

        return simpleAuthenticationInfo;
    }

    // 通过用户名获取用户密码的方法  (可以通过这个方法连接数据库或其他方式)
    private String getPasswordByUsername(String username) {
        //从数据库中通过用户名获取密码
        User user = userDao.getPasswordByUsername(username);
        if (null != user){
            return user.getPassword();
        }
        return null;
    }

    // 使用MD5对密码进行加密
    public static void main(String[] args) {
        // 使用md5 进行加密  如果使用加盐的形式  在后面添加一个字符串用做盐
        Md5Hash md5Hash = new Md5Hash("1234567","admin");
        System.out.println("使用md5加密后的结果是 : " + md5Hash.toString());

    }
}
