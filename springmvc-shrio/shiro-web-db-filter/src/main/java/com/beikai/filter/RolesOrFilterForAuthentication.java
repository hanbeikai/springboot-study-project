package com.beikai.filter;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 自定义 认证过滤器
 */
public class RolesOrFilterForAuthentication extends AuthenticatingFilter {


    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        return false;
    }

    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        return null;
    }
}
