package com.beikai.springboottestdemo.classloadtest;

/**
 * @ClassName ClassLoadDemo2
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/1 19:18
 * @Version 1.0
 **/
public class ClassLoadDemo2{

    public ClassLoadDemo2() {
        System.out.println("ClassLoadDemo2 构造方法");
    }

    {
        System.out.println("ClassLoadDemo2 代码块");
    }

   static {
        System.out.println("ClassLoadDemo2 静态代码块");
    }

}
