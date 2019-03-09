package com.beikai.springboottestdemo.test;

/**
 * @ClassName Outter
 * @Description TODO
 * @Author Admin
 * @Date 2019/2/14 20:46
 * @Version 1.0
 **/
public class Outter {
    static class Inner{
        static int paramOne = 5;
        static final int paramTwo = 5;
        static final int paramThree = new Integer(5);
    }

    public static void main(String[] args){
        System.out.println(Inner.paramOne);
        System.out.println(Inner.paramTwo);
        System.out.println(Inner.paramThree);
    }
}
