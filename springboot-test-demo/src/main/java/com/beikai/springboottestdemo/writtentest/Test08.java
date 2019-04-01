package com.beikai.springboottestdemo.writtentest;

import java.util.Arrays;

/**
 * @ClassName Test08
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/1 23:28
 * @Version 1.0
 *  只使用一个循环完成 二维数组的 创建
 *      [1,   2,   3,   4]
 *      [5,   6,   7,   8]
 *      [9,   10,  11,  12]
 *      [13,  14,  15,  16]
 **/
public class Test08 {
    public static void main(String[] args) {
        int[][] temp = new int[4][4];
        for (int i = 0; i < 16; i++) {
            temp[i/4][i % 4] = i+1;
        }

        for (int i = 0; i < temp.length; i++) {
            int[] ints = temp[i];

            System.out.println(Arrays.toString(ints));
        }
    }
}
