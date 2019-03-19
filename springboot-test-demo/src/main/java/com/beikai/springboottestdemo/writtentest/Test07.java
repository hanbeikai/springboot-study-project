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
        int[] arr = {1,0,0,0,0,0,1,0,1};
        solution2(arr);
    }

    public static int solution(int[] arr){

        int result = 0;

        for (int i = 0; i < arr.length; i++) {

            int before = 0;
            int after = 0;

            if (i < arr.length - 1){
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j] == 1){
                        after = j;
                        break;
                    }

                }
            }

            if (i > 0){
                for (int j = i - 1; j >= 0; j--) {
                    if (arr[j] == 1){
                        before = j;
                        break;
                    }
                    
                }
            }

            int temp = (i - before) < (i - after) ? (i - before) : (i - after);
            if (result < temp){
                result = temp;
            }

        }

        System.out.println(result);

        return result;
    }

    public static int solution2(int[] arr){
        // 存1 的位置的数组
        List<Integer> ints = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1){
                ints.add(i);
            }
        }

        int result = 0;

        for (int i = 0; i < ints.size(); i++) {
            if (i == 0){
                result = ints.get(i);
            }

            if (i == ints.size()-1 && (arr.length - ints.get(i) > result)){
                result = arr.length - 1 - ints.get(i);
            }

            if (i > 0 && i < ints.size() - 1 && ((ints.get(i) - ints.get(i - 1)) < (ints.get(i+1) - ints.get(i))
                    ? (ints.get(i) - ints.get(i - 1)): ((ints.get(i+1) - ints.get(i)))) > result){

                    int before2 = ints.get(i) - ints.get(i - 1) - 1;

                result = before2 % 2 == 0 ? before2 / 2 : (before2 / 2 ) + 1;
            }

        }

        System.out.println(result);

        return result;

    }
}
