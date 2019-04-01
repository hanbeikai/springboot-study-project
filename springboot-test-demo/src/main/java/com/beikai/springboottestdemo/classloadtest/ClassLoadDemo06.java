package com.beikai.springboottestdemo.classloadtest;

/**
 * @ClassName ClassLoadDemo06
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/2 0:29
 * @Version 1.0
 **/
public class ClassLoadDemo06 extends ClassLoadDemo05{
    /**
     * 静态变量
     */
    public static int i = 1;

    /**
     * 静态代码块1
     */
    static {
        System.out.println("ClassLoadDemo06:静态代码块1: " + i );
    }

    /**
     * 非静态变量
     */
    public int j = 1;

    /**
     * 静态代码块2
     */
    static {
        i++;
        System.out.println("ClassLoadDemo06:静态代码块2: " + i);
    }
    /**
     * 构造函数
     */
    public ClassLoadDemo06(){
        super();
        i++;
        j++;
        System.out.println("ClassLoadDemo06:构造函数: i = " + i+" j = " + j);
    }
    /**
     * 非静态语句块
     */
    {
        i++;
        j++;
        System.out.println("ClassLoadDemo06:非静态代码块:  i = " + i+" j = " + j);
    }
    /**
     * 静态方法
     */
    public static void test(){
        i++;
        System.out.println("ClassLoadDemo06:静态方法 i = " + i);
        return;
    }

    /**
     * 非静态方法
     */
    @Override
    public void noStaticTest(){
        i++;
        System.out.println("ClassLoadDemo06:非静态方法 i = " + i+" j = " + j);
        return;
    }

}
