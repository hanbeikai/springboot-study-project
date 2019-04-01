package com.beikai.springboottestdemo.writtentest;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Test07
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/18 21:37
 * @Version 1.0
 * 最近的人的最大距离
 * 用一个数组表示一排座位, 0 表示空位,1 表示有人坐
 * 路人甲想选一个离其他人最远的座位,求最近的人的最大距离
 **/
public class Test07 {

    public static void main(String[] args) {
        int[] arr = {1, 0,0,0, 1, 0, 1};
        solution2(arr);
    }

    public static int solution(int[] arr) {

        int result = 0;

        for (int i = 0; i < arr.length; i++) {

            int before = 0;
            int after = 0;

            if (i < arr.length - 1) {
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j] == 1) {
                        after = j;
                        break;
                    }

                }
            }

            if (i > 0) {
                for (int j = i - 1; j >= 0; j--) {
                    if (arr[j] == 1) {
                        before = j;
                        break;
                    }

                }
            }

            int temp = (i - before) < (i - after) ? (i - before) : (i - after);
            if (result < temp) {
                result = temp;
            }

        }

        System.out.println(result);

        return result;
    }

    public static int solution2(int[] arr) {
        // 存1 的位置的数组
        List<Integer> ints = new ArrayList<>();
        // 获取所有1的位置
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                ints.add(i);
            }
        }

        int result = 0;
        // 遍历所有1 的位置
        for (int i = 0; i < ints.size(); i++) {
            // 对于第一个1而言,最大长度是就是当前1所在的位置
            if (i == 0) {
                result = ints.get(i);
            }

            // 对于最后一个1而言,最大长度是所传进来数组的最大位置减去当前最后一个1所在的位置再减1
            // 判断是否是最后一个位置以及是否当前位置的最大长度大于 第一个1 所在的位子距离
            if (i == ints.size() - 1 && (arr.length - ints.get(i) > result)) {
                result = arr.length - 1 - ints.get(i);
            }

            // 如果在中间,则比较当前位置前一个与后一个各自的距离是否大于之前所获取的位置
            if (i > 0 && i < ints.size() - 1) {
                int temp = ((ints.get(i) - ints.get(i - 1)) > (ints.get(i + 1) - ints.get(i)) ? (ints.get(i) - ints.get(i - 1)) : ((ints.get(i + 1) - ints.get(i)))) ;
                if ((temp % 2 == 0 ? temp / 2 : (temp - 1) / 2) > result){
                    result = temp % 2 == 0 ? temp / 2 : (temp -1) / 2;
                }
            }

        }

        System.out.println(result);

        return result;

    }
}
