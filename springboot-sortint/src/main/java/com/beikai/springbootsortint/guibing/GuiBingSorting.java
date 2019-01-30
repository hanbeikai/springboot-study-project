package com.beikai.springbootsortint.guibing;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName GuiBingSorting
 * @Description TODO
 * @Author Admin
 * @Date 2019/1/30 10:28
 * @Version 1.0
 *  归并排序算法
 **/
public class GuiBingSorting {

    /**
     * 归并排序算法
     *
     *      归并排序算法主要采用二分法(递归)的形式
     *      首先求数组的中间值,然后分别对中间值两边进行排序,排序过程中按照顺序存入临时数组
     *      最后把临时数组的值放入原数组中
     *
     *      归并排序是一个非常高效的排序,能利用完成二叉树的排序效率都不会差
     *
     *      每次合并的时间复杂度是o(n), 二叉树的深度是|log2n|,总的平均时间复杂度为O(nlogn)
     *      并且 无论好坏,时间复杂度都是 O(nlogn)
     *
     *------------------一次递归--------------------
     * ------------------一次递归--------------------
     * ------------------一次递归--------------------
     * ------------------一次递归--------------------
     * left 边 排序后的顺序是 : [1, 4, 2, 5, 2, 4, 7, 5]
     * ------------------一次递归--------------------
     * right 边 排序后的顺序是 : [1, 4, 2, 5, 2, 4, 7, 5]
     * 合并后的顺序是 : [1, 4, 2, 5, 2, 4, 7, 5]
     * left 边 排序后的顺序是 : [1, 4, 2, 5, 2, 4, 7, 5]
     * ------------------一次递归--------------------
     * ------------------一次递归--------------------
     * left 边 排序后的顺序是 : [1, 4, 2, 5, 2, 4, 7, 5]
     * ------------------一次递归--------------------
     * right 边 排序后的顺序是 : [1, 4, 2, 5, 2, 4, 7, 5]
     * 合并后的顺序是 : [1, 4, 2, 5, 2, 4, 7, 5]
     * right 边 排序后的顺序是 : [1, 4, 2, 5, 2, 4, 7, 5]
     * 合并后的顺序是 : [1, 2, 4, 5, 2, 4, 7, 5]
     * left 边 排序后的顺序是 : [1, 2, 4, 5, 2, 4, 7, 5]
     * ------------------一次递归--------------------
     * ------------------一次递归--------------------
     * ------------------一次递归--------------------
     * left 边 排序后的顺序是 : [1, 2, 4, 5, 2, 4, 7, 5]
     * ------------------一次递归--------------------
     * right 边 排序后的顺序是 : [1, 2, 4, 5, 2, 4, 7, 5]
     * 合并后的顺序是 : [1, 2, 4, 5, 2, 4, 7, 5]
     * left 边 排序后的顺序是 : [1, 2, 4, 5, 2, 4, 7, 5]
     * ------------------一次递归--------------------
     * ------------------一次递归--------------------
     * left 边 排序后的顺序是 : [1, 2, 4, 5, 2, 4, 7, 5]
     * ------------------一次递归--------------------
     * right 边 排序后的顺序是 : [1, 2, 4, 5, 2, 4, 7, 5]
     * 合并后的顺序是 : [1, 2, 4, 5, 2, 4, 5, 7]
     * right 边 排序后的顺序是 : [1, 2, 4, 5, 2, 4, 5, 7]
     * 合并后的顺序是 : [1, 2, 4, 5, 2, 4, 5, 7]
     * right 边 排序后的顺序是 : [1, 2, 4, 5, 2, 4, 5, 7]
     * 合并后的顺序是 : [1, 2, 2, 4, 4, 5, 5, 7]
     * 排序完的顺序是 : [1, 2, 2, 4, 4, 5, 5, 7]
     */
    @Test
    public void guibingTest(){
        int[] arr = {1,4,2,5,2,4,7,5};
        guibing(arr);
        System.out.println("排序完的顺序是 : " + Arrays.toString(arr));
    }

    /**
     * 归并排序算法
     * @param arr
     */
    public void guibing(int[] arr){
        // 首先 先创建一个临时数组
        int[] tempArr = new int[arr.length];

        // 调用归并算法
        guibingSort(arr,0,arr.length-1,tempArr);
    }

    /**
     * 归并算法
     * @param arr 目标数组
     * @param start 开始位置
     * @param end 结束位置
     * @param tempArr 临时数组
     */
    public void guibingSort(int[] arr, int start, int end, int[] tempArr) {
        // 判断是否开始下标与结束下标的关系是否满足条件
        System.out.println("------------------一次递归--------------------");
        if (start < end){
            // 获取中间值
            int mid = (start + end) / 2;
            // 排序中间值左边的序列
            guibingSort(arr,start,mid,tempArr);
            System.out.println("left 边 排序后的顺序是 : " + Arrays.toString(arr));
            // 排序中间值右边的序列
            guibingSort(arr,mid+1,end,tempArr);
            System.out.println("right 边 排序后的顺序是 : " + Arrays.toString(arr));
            // 合并排序
            together(arr,start,mid,end,tempArr);
            System.out.println("合并后的顺序是 : " + Arrays.toString(arr));
        }
    }

    /**
     * 合并临时数组
     * @param arr
     * @param start
     * @param mid
     * @param end
     * @param tempArr
     */
    public void together(int[] arr, int start, int mid, int end, int[] tempArr) {

        // 左序列指针
        int left = start;
        // 有序列指针
        int right = mid+1;
        // 临时指针
        int temp = 0;

        // 比较中间值两边的数
        while (left <= mid && right <= end){
            if (arr[left] <= arr[right]){
                tempArr[temp++] = arr[left++];
            }else {
                tempArr[temp++] = arr[right++];
            }
        }

        // 将左边剩余全部添加到临时数组中
        while (left <= mid){
            tempArr[temp++] = arr[left++];
        }

        // 将右侧剩余全部添加到临时数组中
        while (right <= end){
            tempArr[temp++] = arr[right++];
        }

        temp = 0;
        // 把临时数组全部拷进原数组中
        while (start <= end){
            arr[start++] = tempArr[temp++];
        }
    }
}
