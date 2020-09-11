package com.beikai.springboottestdemo.designPattern.proxyPattern.dynamicProxyPattern.invocationHandle;

import com.beikai.springboottestdemo.designPattern.proxyPattern.dynamicProxyPattern.basic.PersonBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/11
 * Time: 8:15 上午
 * Description: 用户访问其他用户的代理类
 * <p>
 * 当前用户只能对 其他用户 执行获取信息的方法以及设置 评分
 */
public class NoOwnInvocationHandle implements InvocationHandler {

    PersonBean personBean;

    public NoOwnInvocationHandle(PersonBean personBean) {
        this.personBean = personBean;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException {

        String name = method.getName();

        // 如果执行的方法是get方法,执行person内容的方法
        if (name.startsWith("get")) {
            return getInvoke(method, args);
        } else if (("setHotOrNotRating").equals(name)) {
            // 如果执行的是 setHotOrNotRating ,则执行 person内容的方法
            return getInvoke(method, args);
        } else if (name.startsWith("set")) {
            // 如果执行别人的set开头的方法,提示非法访问
            throw new IllegalAccessException("非法访问");
        }

        throw new IllegalAccessException("非法操作");
    }

    private Object getInvoke(Method method, Object[] args) throws IllegalAccessException {
        try {
            return method.invoke(personBean, args);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        throw new IllegalAccessException("非法操作!");
    }
}
