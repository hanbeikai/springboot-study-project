package com.beikai.springbootsortint.charu;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName ChaRuSorting
 * @Description TODO
 * @Author Admin
 * @Date 2019/1/29 17:13
 * @Version 1.0
 *  插入排序法  地址:https://www.javazhiyin.com/13397.html
 **/
public class ChaRuSorting {

    /**
     * 插入排序法
     *      插入排序法分为内外循环
     *          外循环选择排序的范围
     *          内循环对外循环范围内的数组进行排序,当外循环选定一个范围后,默认上一次选定分为内的数组已经保证有序了
     *
     *          当保证内循环的条件成立: 即 内循环下标大于零以及当前下标以及当前下标前一个下标的参数有大小关系
     *
     *          满足条件后,进行位置的交换,然后再往前两两比较,最终完成排序
     *
     *          排序的次数相对于冒泡排序以及选择排序:
     *              最多和冒泡排序以及选择排序相同;
     *              最少0次
     *
     * i的大小是 -----------> 0
     * i的大小是 -----------> 1
     * i的大小是 -----------> 2
     * j的大小是 ---> 2
     * 此时数组排序状况是 : [1, 2, 4, 5, 2, 4, 7]
     * i的大小是 -----------> 3
     * i的大小是 -----------> 4
     * j的大小是 ---> 4
     * 此时数组排序状况是 : [1, 2, 4, 2, 5, 4, 7]
     * j的大小是 ---> 3
     * 此时数组排序状况是 : [1, 2, 2, 4, 5, 4, 7]
     * i的大小是 -----------> 5
     * j的大小是 ---> 5
     * 此时数组排序状况是 : [1, 2, 2, 4, 4, 5, 7]
     * i的大小是 -----------> 6
     * 总共统计的次数是 : 4
     * 排序完的顺序是 : [1, 2, 2, 4, 4, 5, 7]
     *
     */
    @Test
    public void charuTest(){
        int[] arr = {1,4,2,5,2,4,7};
        charu(arr);
        System.out.println("排序完的顺序是 : " + Arrays.toString(arr));
    }

    /**
     * 插入排序法
     * @param arr
     */
    public void charu(int[] arr){
        int size = arr.length;
        int count = 0;
        for (int i = 0; i < size; i++) {
            System.out.println("i的大小是 -----------> " + i);
            for (int j = i; (j>0) && (arr[j] < arr[j-1]); j--) {
                count++;
                System.out.println("j的大小是 ---> " + j);
                swap(arr,j,j-1);
                System.out.println("此时数组排序状况是 : " + Arrays.toString(arr));
            }

        }
        System.out.println("总共统计的次数是 : " + count);
    }

    /**
     * 数组内容进行交换
     * @param arr
     * @param x
     * @param y
     */
    public void swap(int[] arr,int x,int y){
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}
