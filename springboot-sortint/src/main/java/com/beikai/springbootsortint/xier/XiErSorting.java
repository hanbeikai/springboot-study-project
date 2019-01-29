package com.beikai.springbootsortint.xier;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName XiErSorting
 * @Description TODO
 * @Author Admin
 * @Date 2019/1/29 17:52
 * @Version 1.0
 * 希尔排序  地址:https://www.javazhiyin.com/13397.html
 **/
public class XiErSorting {
    /**
     * 希尔排序法
     *  希尔排序法有三个循环
     *      while
     *      以及 两个for循环
     *
     *      while 循环的目的获取分组的每组的长度,用于排序
     *      第一个for循环是为了对比每个两个相距为 while 求出的长度的参数之间的大小
     *      第二个for循环是为了排序当前满足条件的参数之前的以while求出的长度为间距的参数
     *
     *      其实后面两个for循环就是 插入排序 相对于 插入排序,更有威力
     *
     *      但是注意的是 : 希尔排序法是不稳定的排序
     *
     * gap的值是 ------> 0 和 i的值是 -------> 4
     * gap的值是 ------> 1 和 i的值是 -------> 5
     * gap的值是 ------> 2 和 i的值是 -------> 6
     * gap的值是 ------> 3 和 i的值是 -------> 7
     * gap=4: 循环结束后数组的序列是 : [1, 4, 2, 5, 2, 4, 7, 5]
     * gap的值是 ------> 0 和 i的值是 -------> 2
     * gap的值是 ------> 1 和 i的值是 -------> 3
     * gap的值是 ------> 2 和 i的值是 -------> 4
     * gap的值是 ------> 3 和 i的值是 -------> 5
     * 内循环数组的序列是 : [1, 4, 2, 5, 2, 5, 7, 5]
     * gap的值是 ------> 4 和 i的值是 -------> 6
     * gap的值是 ------> 5 和 i的值是 -------> 7
     * gap=2: 循环结束后数组的序列是 : [1, 4, 2, 4, 2, 5, 7, 5]
     * gap的值是 ------> 0 和 i的值是 -------> 1
     * gap的值是 ------> 1 和 i的值是 -------> 2
     * 内循环数组的序列是 : [1, 4, 4, 4, 2, 5, 7, 5]
     * gap的值是 ------> 2 和 i的值是 -------> 3
     * gap的值是 ------> 3 和 i的值是 -------> 4
     * 内循环数组的序列是 : [1, 2, 4, 4, 4, 5, 7, 5]
     * 内循环数组的序列是 : [1, 2, 4, 4, 4, 5, 7, 5]
     * gap的值是 ------> 4 和 i的值是 -------> 5
     * gap的值是 ------> 5 和 i的值是 -------> 6
     * gap的值是 ------> 6 和 i的值是 -------> 7
     * 内循环数组的序列是 : [1, 2, 2, 4, 4, 5, 7, 7]
     * gap=1: 循环结束后数组的序列是 : [1, 2, 2, 4, 4, 5, 5, 7]
     * 排序完的顺序是 : [1, 2, 2, 4, 4, 5, 5, 7]
     *
     *
     *
     */
    @Test
    public void xierTest(){
        int[] arr = {1,4,2,5,2,4,7,5};
        xier(arr);
        System.out.println("排序完的顺序是 : " + Arrays.toString(arr));
    }

    /**
     * 希尔排序法
     * @param arr
     */
    public void xier(int[] arr){
        int gap = arr.length/2;
        while (gap >= 1){
            for (int i = gap; i < arr.length; i++) {
                System.out.println("gap的值是 ------> " + (i - gap)
                        +" 和 i的值是 -------> " + i);
                if (arr[i - gap] > arr[i] ){
                    // 缓存当前值
                    int temp = arr[i];
                    int j;

                    for (j = i - gap; (j >= 0) && (temp < arr[j]); j-=gap) {
                        arr[j+gap] = arr[j];
                        System.out.println("内循环数组的序列是 : " + Arrays.toString(arr));
                        arr[j] = temp;
                    }
                }
            }
            System.out.format("gap=%d: ",gap);
            System.out.println("循环结束后数组的序列是 : " + Arrays.toString(arr));
            gap=gap/2;
        }

    }
}
