package com.beikai.springboottestdemo.classloadtest;

/**
 * @ClassName ClassLoadDemo3
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/1 19:20
 * @Version 1.0
 *
 **/
public class ClassLoadDemo3 extends ClassLoadDemo2{
    public ClassLoadDemo3() {
        System.out.println("ClassLoadDemo3 构造方法");
    }

    {
        System.out.println("ClassLoadDemo3 代码块");
    }

    static {
        System.out.println("ClassLoadDemo3 静态代码块");
    }



    public static void main(String[] args) {
        new ClassLoadDemo2();


        /**
         * ClassLoadDemo2 静态代码块
         * ClassLoadDemo3 静态代码块
         * ClassLoadDemo2 代码块
         * ClassLoadDemo2 构造方法
         * ClassLoadDemo3 代码块
         * ClassLoadDemo3 构造方法
         *
         * 子类继承父类,如果父类有静态静态代码块,父类的静态代码块先启动,如果子类有静态代码块,子类的静态代码块在父类静态代码块之后再父类代码块和构造方法之前
         * 父类的代码快和构造方法先与子类的代码块和构造方法
         *
         */


    }
}
