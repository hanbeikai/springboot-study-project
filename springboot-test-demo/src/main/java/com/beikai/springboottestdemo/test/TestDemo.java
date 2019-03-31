package com.beikai.springboottestdemo.test;

import com.beikai.springboottestdemo.singletest.SingleDemo07;
import com.beikai.springboottestdemo.singletest.SingleDemo09;
import org.junit.Test;

/**
 * @ClassName TestDemo
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/9 18:32
 * @Version 1.0
 * <p>
 * 测试单例模式
 **/
public class TestDemo {

    @Test
    public void test01() {
        SingleDemo07 singleDemo07 = SingleDemo07.getInstance();

        SingleDemo07 singleDemo071 = SingleDemo07.getInstance();

        System.out.println("创建的第一个对象 : " + singleDemo07);
        System.out.println("创建的第二个对象 : " + singleDemo071);

        TestDemo testDemo = new TestDemo();

        TestDemo testDemo1 = new TestDemo();

        System.out.println("创建的第三个对象 : " + testDemo);
        System.out.println("创建的第四个对象 : " + testDemo1);

        SingleDemo09 singleDemo09 = SingleDemo09.SINGLE_DEMO_09;
        SingleDemo09 singleDemo10 = SingleDemo09.SINGLE_DEMO_09;

        singleDemo09.setName("张三");

        System.out.println("枚举类的对象 : " + singleDemo09.getName());
        System.out.println("枚举类的对象 : " + singleDemo10.getName());


    }

    @Test
    public void test02() {

        Test04 test04 = new Test04().setAge("12").setName("张三").setUsername("zhangsan");

        System.out.println(test04);

    }

    public static void main(String[] args) {
    }

}
