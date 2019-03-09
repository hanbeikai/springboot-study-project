package com.beikai.springboottestdemo.sorttest;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName XuanZeSortDemo
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/9 20:02
 * @Version 1.0
 *  选择排序
 **/
public class XuanZeSortDemo {

    @Test
    public void test(){
        int[] arr = {343,6,3,88,2,9,37};
        xuanze(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void xuanze(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
            
        }

    }

}
