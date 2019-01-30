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
     *
     *  快速排序法的算法是:
     *      根据要求,如果是从左向右依次增大;
     *      对一个数组,首先设置一个基数,然后分别从右和从左依次与基数进行对比
     *          从右边开始时: 遍历一个数,如果大于基数,不变,下标减一,如果小于基数,
     *          这个数与从左向右遍历的下标对应的值进行互换.然后从右向左遍历的下标加一
     *          (默认都是从右开始,第一次互换时左侧被换的数其实是基数)
     *
     *          右侧数转换位置后,开始从左侧遍历,如果小于基数,不变,下标加一,如果大于基数,
     *          这个数与从右向左遍历的下标对应的值进行互换,然后从右向左遍历的下标减一;
     *          (经过一轮之后,之前保存的基数对应的值赋给从左向右遍历的下标,达到一个参数互换的目的)
     *
     *          然后采用递归的方式,重复这个操作,只到从右向左的下标与从左向右的下标相遇,完成整个排序
     *
     *          排序的次数 :
     *
     *遍历后b的值是 : 6
     * 遍历后b的值是 : 5
     * 遍历后b的值是 : 4
     * 遍历后b的值是 : 3
     * 遍历后b的值是 : 2
     * 遍历后b的值是 : 1
     * 遍历后b的值是 : 0
     * 经过一次排序后的结果是 : [1, 4, 2, 5, 2, 4, 7, 5]
     * 遍历后b的值是 : 6
     * 遍历后b的值是 : 5
     * 右侧比较后的数组是 : [1, 4, 2, 5, 2, 4, 7, 5]
     * 遍历后a的值是 : 3
     * 左侧比较后的数组是 : [1, 4, 2, 5, 2, 5, 7, 5]
     * 右侧比较后的数组是 : [1, 4, 2, 2, 2, 5, 7, 5]
     * 经过一次排序后的结果是 : [1, 4, 2, 2, 4, 5, 7, 5]
     * 右侧比较后的数组是 : [1, 2, 2, 2, 4, 5, 7, 5]
     * 遍历后a的值是 : 3
     * 经过一次排序后的结果是 : [1, 2, 2, 4, 4, 5, 7, 5]
     * 右侧比较后的数组是 : [1, 2, 2, 4, 4, 5, 7, 5]
     * 经过一次排序后的结果是 : [1, 2, 2, 4, 4, 5, 7, 5]
     * 经过一次排序后的结果是 : [1, 2, 2, 4, 4, 5, 7, 5]
     * 右侧比较后的数组是 : [1, 2, 2, 4, 4, 5, 7, 5]
     * 左侧比较后的数组是 : [1, 2, 2, 4, 4, 5, 7, 7]
     * 经过一次排序后的结果是 : [1, 2, 2, 4, 4, 5, 5, 7]
     * 经过一次排序后的结果是 : [1, 2, 2, 4, 4, 5, 5, 7]
     * 经过一次排序后的结果是 : [1, 2, 2, 4, 4, 5, 5, 7]
     * 排序完的顺序是 : [1, 2, 2, 4, 4, 5, 5, 7]
     * 排序的次数是 : 17
     *
     */
    @Test
    public void kuaisuTest() {
        int[] arr = {1, 4, 2, 5, 2, 4, 7, 5};
        int count = 0;
        count = kuaisu2(arr, 0, arr.length - 1,count);
        System.out.println("排序完的顺序是 : " + Arrays.toString(arr));
        System.out.println("排序的次数是 : " + count);
    }

    /**
     * 快速排序法 2
     */
    public int kuaisu2(int[] arr, int x, int y,int count) {
        count++;
        // 如果起始值小于结束值 直接返回
        if (x > y) {
            return count;
        }
        int a = x;
        int b = y;
        // 设置基数
        int z = arr[a];
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
        count = kuaisu2(arr, x, a-1,count);
        count = kuaisu2(arr,a+1, y,count);

        return count;
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

}
