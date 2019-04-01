package com.beikai.springboottestdemo.classloadtest;

import org.junit.Test;

/**
 * @ClassName ClassLoadDemo
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/10 9:37
 * @Version 1.0
 *
 * 用于 测试类加载机制
 *
 *  结果 :
 *      加载了静态代码块
 *      加载了代码块
 *      加载了构造方法
 *      调用了静态方法
 *      张三
 **/
public class ClassLoadDemo {

    private static String name = "张三";

    private String age;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        ClassLoadDemo.name = name;
    }

    public ClassLoadDemo(){
        System.out.println("ClassLoadDemo --- 加载了构造方法");
    }

    {
        System.out.println("ClassLoadDemo --- 加载了代码块");
    }

    static {
        System.out.println("ClassLoadDemo --- 加载了静态代码块");
    }

    public static String getThing(){
        System.out.println("ClassLoadDemo --- 调用了静态方法");

        return getName();
    }


    @Test
    public void test(){
        System.out.println(ClassLoadDemo.getThing());
    }


}
