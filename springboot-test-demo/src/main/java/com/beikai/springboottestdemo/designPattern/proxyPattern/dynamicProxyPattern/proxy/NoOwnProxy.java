package com.beikai.springboottestdemo.designPattern.proxyPattern.dynamicProxyPattern.proxy;

import com.beikai.springboottestdemo.designPattern.proxyPattern.dynamicProxyPattern.basic.PersonBean;
import com.beikai.springboottestdemo.designPattern.proxyPattern.dynamicProxyPattern.invocationHandle.NoOwnInvocationHandle;

import java.lang.reflect.Proxy;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/11
 * Time: 8:24 上午
 * Description: 获取用户访问别的用户的代理对象
 */
public class NoOwnProxy {
    public static PersonBean getNoOwnProxy(PersonBean personBean){
       return (PersonBean) Proxy.newProxyInstance(
               personBean.getClass().getClassLoader(),
               personBean.getClass().getInterfaces(),
               new NoOwnInvocationHandle(personBean));
    }
}
