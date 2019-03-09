package com.beikai.springboottestdemo.test;

/**
 * @ClassName StaticTest
 * @Description TODO
 * @Author Admin
 * @Date 2019/2/24 17:02
 * @Version 1.0
 **/
public class StaticTest {

    StaticTest() {
        System.out.println("3");
        System.out.println("a="+a+",b="+b);
    }

    public static void main(String[] args) {
        staticFunction();
    }

    static StaticTest st = new StaticTest();

    static {
        System.out.println("1");
    }


    {
        System.out.println("2");
    }

    public static void staticFunction(){
        System.out.println("4");
    }

    int a=110;
    static int b =112;
}
