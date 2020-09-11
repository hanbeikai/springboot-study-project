package com.beikai.springboottestdemo.designPattern.proxyPattern.dynamicProxyPattern.proxy;

import com.beikai.springboottestdemo.designPattern.proxyPattern.dynamicProxyPattern.basic.PersonBean;
import com.beikai.springboottestdemo.designPattern.proxyPattern.dynamicProxyPattern.invocationHandle.OwnInvocationHandle;

import java.lang.reflect.Proxy;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/11
 * Time: 8:24 上午
 * Description: 用户自己的代理类
 */
public class OwnProxy {

    public static PersonBean getOwnProxy(PersonBean personBean){
       return (PersonBean) Proxy.newProxyInstance(
               personBean.getClass().getClassLoader(),
               personBean.getClass().getInterfaces(),
               new OwnInvocationHandle(personBean));
    }
}
