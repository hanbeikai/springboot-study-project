package com.beikai.springboottestdemo.test;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName test03
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/10 18:36
 * @Version 1.0
 *
 *  java 输入一个整数,统计奇数位于偶数位之和
 **/
public class test03 {

    @Test
    public void test1(){

        int[] b = new int[2];
        test03(234555,b);

        System.out.println(Arrays.toString(b));
    }

    public void test(int a,int[] b){
        String s = a+"";
        char[] chars = s.toCharArray();

        int count = 0;
        for (int i = chars.length -1; i >= 0; i--) {
            count++;
            if (count % 2 == 0){
                b[0] += Integer.parseInt(chars[i]+"");
                System.out.println("偶数是 : " + chars[i]);
            }else {
                b[1] += chars[i] - '0';
                System.out.println("奇数是 : " + chars[i]);
            }
        }
    }

    public void test03(int a,int[] b){
        boolean flag = true;

        int count = 0;

        while (flag){
            count++;
            int temp = a % 10;
            if (count % 2 == 0){
                b[0] += temp;
                System.out.println("偶数是 : " + temp);
            }else {
                b[1] += temp;
                System.out.println("奇数是 : " + temp);
            }
            a = a / 10;

            if (a == 0){
                flag = false;
            }

        }
    }
}
