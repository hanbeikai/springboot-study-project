package com.beikai.springboottestdemo.sorttest;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName KuaiSuSortDemo
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/9 20:01
 * @Version 1.0
 *  快速排序
 **/
public class KuaiSuSortDemo {

    @Test
    public void test(){
        int[] arr = {1,2,4,3,7,65,00,7,33};
        KuaiSuMethod(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    private void KuaiSuMethod(int[] arr, int start, int end) {

        if (start > end) {
            return;
        }

        int a = start;
        int b = end;
        int temp = arr[a];

        // 便利数组,分别从两边开始与temp比较大小,从右往左,如果小,替换,从坐往右,如果大,替换
        while (a < b){
            // 从右向左对比,如果右边的比左边的大,下标向左移动一位
            while (a < b && arr[b] > temp){
                b--;
            }
            // 如果存在 右边的比左边的小,替换,左边的下标向右移动一位
            if (a < b){
                arr[a] = arr[b];
                a++;
            }
            // 从左向右对比,如果左边的比右边的小,下标向右移动一位
            while (a < b && arr[a] < temp){
                a++;
            }
            // 如果左边的大于右边的,替换,右边的下标向左移动一位
            if (a < b){
                arr[b] = arr[a];
                b--;
            }
        }

        // 当两个下标相邻的时候,此时已经粗略排序过,然后对此时 temp为中间值
        arr[a] = temp;
        // 排序中间值左面的
        KuaiSuMethod(arr,start,a-1);
        // 排序中间值右面的
        KuaiSuMethod(arr,a+1,end);

    }
}
