package com.beikai.springboottestdemo.test;

/**
 * @ClassName TestInteger
 * @Description TODO
 * @Author Admin
 * @Date 2019/2/12 23:15
 * @Version 1.0
 **/
public class TestInteger {
    public static final int ENDNUMBER = Integer.MAX_VALUE - 1;
    public static final int STARTNUMBER = ENDNUMBER - 2;

    public static void main(String[] args) {
        int count = 0;
        for (int i = STARTNUMBER; i <= ENDNUMBER; i++) {
            count++;
            System.out.println(count);
        }
    }

}
