package com.beikai.springboottestdemo.designPattern.proxyPattern.dynamicProxyPattern;

import com.beikai.springboottestdemo.designPattern.proxyPattern.dynamicProxyPattern.basic.PersonBean;
import com.beikai.springboottestdemo.designPattern.proxyPattern.dynamicProxyPattern.impl.PersonBeanImpl;
import com.beikai.springboottestdemo.designPattern.proxyPattern.dynamicProxyPattern.proxy.NoOwnProxy;
import com.beikai.springboottestdemo.designPattern.proxyPattern.dynamicProxyPattern.proxy.OwnProxy;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/11
 * Time: 8:22 上午
 * Description: 动态代理 测试demo
 */
public class InvocationTestDemo {
    public static void main(String[] args) {

        InvocationTestDemo invocationTestDemo = new InvocationTestDemo();
        invocationTestDemo.drive();
    }


    private void drive() {

        // 创建用户1
        PersonBean personBean = new PersonBeanImpl("张三","男","活着");
        // 获取用户1的代理
        PersonBean ownProxy = OwnProxy.getOwnProxy(personBean);
        // 获取当前用户名字
        System.out.println("当前用户的名字 : " + ownProxy.getName());
        // 设置当前用户兴趣
        ownProxy.setInterests("努力的活着");
        System.out.println("用户自己的代理设置兴趣成功 !");

        // 用户给自己设置评分
        try {
            ownProxy.setHotOrNotRating(10);
        } catch (Exception e) {
            System.out.println("用户给自己设置评分失败 : " + getStackTrace(e.getCause()));
        }

        System.out.println("用户的评分是 : " + ownProxy.getHotOrNotRating());

        // 获取当前用户对别的用户的代理对象
        PersonBean noOwnProxy = NoOwnProxy.getNoOwnProxy(personBean);
        System.out.println("代理的用户名是 : " + noOwnProxy.getName());

        try {
            noOwnProxy.setInterests("无声的活着!");
        } catch (Exception e) {
            System.out.println("设置兴趣出错 : " + getStackTrace(e.getCause()));
        }

        noOwnProxy.setHotOrNotRating(20);
        System.out.println("对别的用户设置 评分 成功");

        System.out.println("用户的评分是 : " + noOwnProxy.getHotOrNotRating());


    }

    /**
     * 获取错误的堆栈信息
     * @param throwable
     * @return
     */
    public String getStackTrace(Throwable throwable){
        StringWriter stringWriter=new StringWriter();
        PrintWriter printWriter=new PrintWriter(stringWriter);

        try {
            throwable.printStackTrace(printWriter);
            return stringWriter.toString();
        }finally {
            printWriter.close();
        }

    }
}
