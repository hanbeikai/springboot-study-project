package com.beikai.springboottestdemo.test;

/**
 * @ClassName Test04
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/10 19:50
 * @Version 1.0
 *  测试 在 for 循环中添加方法
 **/
public class Test04 {

    public static void main(String[] args) {
        int i = 0;

        for (foo('a'); foo('b') && ( i < 2); foo('c')) {
            i++;
        }
    }

    public static boolean foo(char c){
        System.out.print(c);
        return true;
    }


}
