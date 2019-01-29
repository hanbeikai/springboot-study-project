package com.beikai.springbootsortint.kuaisu;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName KuaiSuSorting
 * @Description TODO
 * @Author Admin
 * @Date 2019/1/29 21:04
 * @Version 1.0
 * 快速排序法  地址:https://www.javazhiyin.com/13397.html
 **/
public class KuaiSuSorting {

    /**
     * 快速排序法
     */
    @Test
    public void kuaisuTest() {
        int[] arr = {1, 4, 2, 5, 2, 4, 7, 5};
        kuaisu2(arr, 0, arr.length - 1);
        System.out.println("排序完的顺序是 : " + Arrays.toString(arr));
    }

    /**
     * 快速排序法 1
     */
    public void kuaisu(int[] arr, int x, int y) {
        // 如果起始值小于结束值 直接返回
        if (x > y) {
            return;
        }
        int a = x;
        int b = y;
        // 设置基数
        int z = arr[x];
        while (a < b) {
            // 从右侧遍历 a和b相遇或出现小于基准的数
            while (a < b && arr[b] >= z) {
                b--;
                System.out.println("遍历后b的值是 : " + b);
            }
            if (arr[b] <= z) {
                int temp = arr[b];
                arr[b] = arr[a];
                arr[a] = temp;
                System.out.println("右侧比较后的数组是 : " + Arrays.toString(arr));
            }

            while (a < b && arr[a] <= z) {
                a++;
                System.out.println("遍历后a的值是 : " + a);
            }
            if (arr[a] >= z) {
                int temp = arr[a];
                arr[a] = arr[b];
                arr[b] = temp;
                System.out.println("左侧比较后的数组是 : " + Arrays.toString(arr));
            }
        }

        System.out.println("经过一次排序后的结果是 : " + Arrays.toString(arr));
        // 递归调用 基点前的排序 基点后的排序
        if (a > x) {
            kuaisu(arr, x, a - 1);
        }
        if (y > b) {
            kuaisu(arr, b + 1, y);
        }
    }

    /**
     * 快速排序法 2
     */
    public void kuaisu2(int[] arr, int x, int y) {
        // 如果起始值小于结束值 直接返回
        if (x > y) {
            return;
        }
        int a = x;
        int b = y;
        // 设置基数
        int z = arr[x];
        while (a < b) {
            // 从右侧遍历 a和b相遇或出现小于基准的数
            while (a < b && arr[b] > z) {
                b--;
                System.out.println("遍历后b的值是 : " + b);
            }
            if (a < b) {
                arr[a] = arr[b];
                a++;
                System.out.println("右侧比较后的数组是 : " + Arrays.toString(arr));
            }

            while (a < b && arr[a] < z) {
                a++;
                System.out.println("遍历后a的值是 : " + a);
            }
            if (a < b) {
                arr[b] = arr[a];
                b--;
                System.out.println("左侧比较后的数组是 : " + Arrays.toString(arr));
            }
        }

        arr[a] = z;
        System.out.println("经过一次排序后的结果是 : " + Arrays.toString(arr));
        // 递归调用 基点前的排序 基点后的排序
        kuaisu2(arr, x, a - 1);
        kuaisu2(arr,a+1, y);

    }
}
