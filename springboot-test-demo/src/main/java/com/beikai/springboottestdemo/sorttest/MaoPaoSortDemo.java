package com.beikai.springboottestdemo.sorttest;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName MaoPaoSortDemo
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/9 20:00
 * @Version 1.0
 *  冒泡排序
 **/
public class MaoPaoSortDemo {

    @Test
    public void maopaotest(){
        int[] arr = {1,5,6,3,7,2,8};

        maopao(arr);

        System.out.println(Arrays.toString(arr));
    }

    public void maopao(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
}
