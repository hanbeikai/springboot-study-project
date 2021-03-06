package com.beikai.springbootsortint.maopao;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName MaoPaoSorting
 * @Description TODO
 * @Author Admin
 * @Date 2019/1/29 16:13
 * @Version 1.0
 * 冒泡排序   地址:https://www.javazhiyin.com/13397.html
 **/
public class MaoPaoSorting {

    /**
     * 冒泡排序
     *  冒泡排序使用双循环的方式,内循环进行两两比较,找出两两之间最大值或最小值,如同冒泡的形式向后移动
     *      外循环的作用是用于计算比较的位数
     *
     *      经过内循环比较一次后,数组中的最大值或最小值已经放在最后,下次比较的时候就可以不比较最后一个,
     *      所以内循环的范围为 arr.length - 1 - i
     *
     *      冒泡排序内循环每次都是从第一个位置开始进行两两比较的
     *
     *      冒泡排序的比较次数为
     *          数组为奇数 : (arr.length) * ((arr.length-1)/2)
     *          数组为偶数 : (arr.length - 1) * ((arr.length)/2)
     *
     *      i的大小是 -----------> 0
     * j的大小是 ---> 0
     * 此时数组排序状况是 : [1, 4, 2, 5, 2, 4, 7, 8, 9, 4]
     * j的大小是 ---> 1
     * 此时数组排序状况是 : [1, 2, 4, 5, 2, 4, 7, 8, 9, 4]
     * j的大小是 ---> 2
     * 此时数组排序状况是 : [1, 2, 4, 5, 2, 4, 7, 8, 9, 4]
     * j的大小是 ---> 3
     * 此时数组排序状况是 : [1, 2, 4, 2, 5, 4, 7, 8, 9, 4]
     * j的大小是 ---> 4
     * 此时数组排序状况是 : [1, 2, 4, 2, 4, 5, 7, 8, 9, 4]
     * j的大小是 ---> 5
     * 此时数组排序状况是 : [1, 2, 4, 2, 4, 5, 7, 8, 9, 4]
     * j的大小是 ---> 6
     * 此时数组排序状况是 : [1, 2, 4, 2, 4, 5, 7, 8, 9, 4]
     * j的大小是 ---> 7
     * 此时数组排序状况是 : [1, 2, 4, 2, 4, 5, 7, 8, 9, 4]
     * j的大小是 ---> 8
     * 此时数组排序状况是 : [1, 2, 4, 2, 4, 5, 7, 8, 4, 9]
     * i的大小是 -----------> 1
     * j的大小是 ---> 0
     * 此时数组排序状况是 : [1, 2, 4, 2, 4, 5, 7, 8, 4, 9]
     * j的大小是 ---> 1
     * 此时数组排序状况是 : [1, 2, 4, 2, 4, 5, 7, 8, 4, 9]
     * j的大小是 ---> 2
     * 此时数组排序状况是 : [1, 2, 2, 4, 4, 5, 7, 8, 4, 9]
     * j的大小是 ---> 3
     * 此时数组排序状况是 : [1, 2, 2, 4, 4, 5, 7, 8, 4, 9]
     * j的大小是 ---> 4
     * 此时数组排序状况是 : [1, 2, 2, 4, 4, 5, 7, 8, 4, 9]
     * j的大小是 ---> 5
     * 此时数组排序状况是 : [1, 2, 2, 4, 4, 5, 7, 8, 4, 9]
     * j的大小是 ---> 6
     * 此时数组排序状况是 : [1, 2, 2, 4, 4, 5, 7, 8, 4, 9]
     * j的大小是 ---> 7
     * 此时数组排序状况是 : [1, 2, 2, 4, 4, 5, 7, 4, 8, 9]
     * i的大小是 -----------> 2
     * j的大小是 ---> 0
     * 此时数组排序状况是 : [1, 2, 2, 4, 4, 5, 7, 4, 8, 9]
     * j的大小是 ---> 1
     * 此时数组排序状况是 : [1, 2, 2, 4, 4, 5, 7, 4, 8, 9]
     * j的大小是 ---> 2
     * 此时数组排序状况是 : [1, 2, 2, 4, 4, 5, 7, 4, 8, 9]
     * j的大小是 ---> 3
     * 此时数组排序状况是 : [1, 2, 2, 4, 4, 5, 7, 4, 8, 9]
     * j的大小是 ---> 4
     * 此时数组排序状况是 : [1, 2, 2, 4, 4, 5, 7, 4, 8, 9]
     * j的大小是 ---> 5
     * 此时数组排序状况是 : [1, 2, 2, 4, 4, 5, 7, 4, 8, 9]
     * j的大小是 ---> 6
     * 此时数组排序状况是 : [1, 2, 2, 4, 4, 5, 4, 7, 8, 9]
     * i的大小是 -----------> 3
     * j的大小是 ---> 0
     * 此时数组排序状况是 : [1, 2, 2, 4, 4, 5, 4, 7, 8, 9]
     * j的大小是 ---> 1
     * 此时数组排序状况是 : [1, 2, 2, 4, 4, 5, 4, 7, 8, 9]
     * j的大小是 ---> 2
     * 此时数组排序状况是 : [1, 2, 2, 4, 4, 5, 4, 7, 8, 9]
     * j的大小是 ---> 3
     * 此时数组排序状况是 : [1, 2, 2, 4, 4, 5, 4, 7, 8, 9]
     * j的大小是 ---> 4
     * 此时数组排序状况是 : [1, 2, 2, 4, 4, 5, 4, 7, 8, 9]
     * j的大小是 ---> 5
     * 此时数组排序状况是 : [1, 2, 2, 4, 4, 4, 5, 7, 8, 9]
     * i的大小是 -----------> 4
     * j的大小是 ---> 0
     * 此时数组排序状况是 : [1, 2, 2, 4, 4, 4, 5, 7, 8, 9]
     * j的大小是 ---> 1
     * 此时数组排序状况是 : [1, 2, 2, 4, 4, 4, 5, 7, 8, 9]
     * j的大小是 ---> 2
     * 此时数组排序状况是 : [1, 2, 2, 4, 4, 4, 5, 7, 8, 9]
     * j的大小是 ---> 3
     * 此时数组排序状况是 : [1, 2, 2, 4, 4, 4, 5, 7, 8, 9]
     * j的大小是 ---> 4
     * 此时数组排序状况是 : [1, 2, 2, 4, 4, 4, 5, 7, 8, 9]
     * i的大小是 -----------> 5
     * j的大小是 ---> 0
     * 此时数组排序状况是 : [1, 2, 2, 4, 4, 4, 5, 7, 8, 9]
     * j的大小是 ---> 1
     * 此时数组排序状况是 : [1, 2, 2, 4, 4, 4, 5, 7, 8, 9]
     * j的大小是 ---> 2
     * 此时数组排序状况是 : [1, 2, 2, 4, 4, 4, 5, 7, 8, 9]
     * j的大小是 ---> 3
     * 此时数组排序状况是 : [1, 2, 2, 4, 4, 4, 5, 7, 8, 9]
     * i的大小是 -----------> 6
     * j的大小是 ---> 0
     * 此时数组排序状况是 : [1, 2, 2, 4, 4, 4, 5, 7, 8, 9]
     * j的大小是 ---> 1
     * 此时数组排序状况是 : [1, 2, 2, 4, 4, 4, 5, 7, 8, 9]
     * j的大小是 ---> 2
     * 此时数组排序状况是 : [1, 2, 2, 4, 4, 4, 5, 7, 8, 9]
     * i的大小是 -----------> 7
     * j的大小是 ---> 0
     * 此时数组排序状况是 : [1, 2, 2, 4, 4, 4, 5, 7, 8, 9]
     * j的大小是 ---> 1
     * 此时数组排序状况是 : [1, 2, 2, 4, 4, 4, 5, 7, 8, 9]
     * i的大小是 -----------> 8
     * j的大小是 ---> 0
     * 此时数组排序状况是 : [1, 2, 2, 4, 4, 4, 5, 7, 8, 9]
     * 总共统计的次数是 : 45
     * 排序完的顺序是 : [1, 2, 2, 4, 4, 4, 5, 7, 8, 9]
     */
    @Test
    public void MaopaoTest(){
        int[] arr = {1,4,2,5,2,4,7};
        maopao(arr);
        System.out.println("排序完的顺序是 : " + Arrays.toString(arr));
    }

    /**
     * 冒泡排序算法
     * @param arr
     * @return
     */
    public void maopao(int[] arr){
        int size = arr.length;
        int count = 0;
        for (int i = 0; i < size - 1; i++) {
            System.out.println("i的大小是 -----------> " + i);
            for (int j = 0; j < size - 1 - i; j++) {
                count++;
                System.out.println("j的大小是 ---> " + j);
                if (arr[j] > arr[j + 1]){
                    swap(arr,j,j+1);
                }
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
