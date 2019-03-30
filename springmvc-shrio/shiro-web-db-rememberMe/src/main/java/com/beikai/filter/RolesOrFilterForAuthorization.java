package com.beikai.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

/**
 * 自定义 授权过滤器
 */
public class RolesOrFilterForAuthorization extends AuthorizationFilter {
    /**
     * 设置 当主体包含某个角色的时候是可以访问的
     * @param servletRequest
     * @param servletResponse
     * @param o
     * @return
     * @throws Exception
     */
    protected boolean isAccessAllowed(javax.servlet.ServletRequest servletRequest,
                                      javax.servlet.ServletResponse servletResponse,
                                      Object o) throws Exception {
        // 获取主体信息
        Subject subject = getSubject(servletRequest,servletResponse);
        // 获取角色信息
        String[] roles = (String[]) o;
        // 判断角色信息是否为null  说明是可以访问的 不需要任何权限
        if (roles == null || roles.length == 0){
            return true;
        }
        // 遍历role数组,当前主体包含某个角色的时候 可以访问
        for (String role : roles) {
            if (subject.hasRole(role)){
                return true;
            }
        }
        return false;
    }
}
