package com.beikai.springboottestdemo.designPattern.proxyPattern.dynamicProxyPattern.invocationHandle;

import com.beikai.springboottestdemo.designPattern.proxyPattern.dynamicProxyPattern.basic.PersonBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/11
 * Time: 8:06 上午
 * Description: 用户对自己的操作的代理 , 实现 调用处理器 接口
 * <p>
 * 当前用户只能对 自己 执行设置的方法和获取信息的方法,不能对自己进行评分
 */
public class OwnInvocationHandle implements InvocationHandler {

    private PersonBean personBean;

    public OwnInvocationHandle(PersonBean personBean) {
        this.personBean = personBean;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException {
        String name = method.getName();
        if (name.startsWith("get")) {
            // 如果调用的方法是get开头的,就调用person内部的方法
            return getInvoke(method, args);
        } else if (("setHotOrNotRating").equals(name)) {
            // 如果调用的方法是 setHotOrNotRating ,抛出没有操作异常
            throw new IllegalAccessException("没有操作权限!");
        } else if (name.startsWith("set")) {
            // 如果是执行的 set 方法,除了 setHotOrNotRating 方法,就执行 person 内部的方法
            return getInvoke(method, args);
        }

        throw new IllegalAccessError("非法访问!");
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
