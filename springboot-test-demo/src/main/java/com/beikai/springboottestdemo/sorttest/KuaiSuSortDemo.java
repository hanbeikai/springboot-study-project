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
        kuaisu(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    public void kuaisu(int[] arr,int start,int end){

        if (start > end){
            return;
        }

        int a = start;
        int b = end;
        int temp = arr[a];

        while (a < b){
            while (a < b && arr[b] > temp){
                b--;
            }

            if (a < b){
                arr[a] = arr[b];
                a++;
            }

            while (a < b && arr[a] < temp){
                a++;
            }
            if (a<b){
                arr[b] = arr[a];
                b--;
            }
        }

        arr[a] = temp;

        kuaisu(arr,start,a-1);
        kuaisu(arr,a+1,end);

    }
}
