package com.beikai.springboottestdemo.test;

/**
 * @ClassName Test05
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/14 22:41
 * @Version 1.0
 **/
public class Test05 {
    private int count;
    public static void main(String[] args) {
        Test05 test05 = new Test05(33);
        System.out.println(test05.count);
    }
    Test05(int v){
        count = v;
    }
}

class Value{
    static boolean val;

    public static void main(String[] args) {
        // System.out.println(val);
        for (int i = 0; i <= 10; i++) {
            Integer k = new Integer(i);
        }
        System.out.println("hello world");
    }
}
