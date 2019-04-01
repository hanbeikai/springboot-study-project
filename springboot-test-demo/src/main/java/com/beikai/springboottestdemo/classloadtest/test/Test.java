package com.beikai.springboottestdemo.classloadtest.test;

import com.beikai.springboottestdemo.classloadtest.ClassLoadDemo06;
import com.beikai.springboottestdemo.classloadtest.ClassLoadDemo3;

/**
 * @ClassName Test
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/1 19:27
 * @Version 1.0
 **/
public class Test {

    @org.junit.Test
    public void test(){
        //new ClassLoadDemo2();
        /**
         * ClassLoadDemo2 静态代码块
         * ClassLoadDemo2 代码块
         * ClassLoadDemo2 构造方法
         */

        new ClassLoadDemo3();
        /**
         *ClassLoadDemo2 静态代码块
         * ClassLoadDemo3 静态代码块
         * ClassLoadDemo2 代码块
         * ClassLoadDemo2 构造方法
         * ClassLoadDemo3 代码块
         * ClassLoadDemo3 构造方法
         */


    }

    @org.junit.Test
    public void test2(){
        ClassLoadDemo06 classLoadDemo06 = new ClassLoadDemo06();
        classLoadDemo06.noStaticTest();

        /**
         * ClassLoadDemo05:静态代码块1: 1
         * ClassLoadDemo05:静态代码块2: 2
         * ClassLoadDemo06:静态代码块1: 1
         * ClassLoadDemo06:静态代码块2: 2
         * ClassLoadDemo05:非静态代码块:  i = 3 j = 2
         * ClassLoadDemo05:构造函数: i = 4 j = 3
         * ClassLoadDemo06:非静态代码块:  i = 3 j = 2
         * ClassLoadDemo06:构造函数: i = 4 j = 3
         * ClassLoadDemo06:非静态方法 i = 5 j = 3
         */
    }
}
