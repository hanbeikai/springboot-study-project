package com.beikai.springboottestdemo.writtentest;

/**
 * @ClassName Test06
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/18 20:22
 * @Version 1.0
 *  城市天际线
 *  用一个二维数组,grid来表示城市天际线,二维数组中的每个数代表相应位置上建筑的高度,从横向和纵向观察到最高点,构成城市天际线,集城市天际线有每一列的最大值两个
 *  一维数组构成,问:
 *      在保持城市天际线不变的情况下,所有建筑加起来的总高度最多还能增加多少,每个建筑增加的高度可以不一样,建筑的高度也可以时0
 **/
public class Test06 {

    public static void main(String[] args) {
        int[][] arr = {{3,0,8,4},{2,4,5,7},{9,2,6,3},{0,3,1,0}};

        solution(arr);
    }

    public static int solution(int[][] arr){

        int[] lineMax = new int[arr.length];
        int[] columnMax = new int[arr[0].length];

        int result = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] > lineMax[i]){
                    lineMax[i] = arr[i][j];
                }
                if (arr[i][j] > columnMax[j]){
                    columnMax[j] = arr[i][j];
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                result += (columnMax[j] < lineMax[i] ? columnMax[j] : lineMax[i]) - arr[i][j];
            }
        }

        System.out.println(result);

        return result;
    }

}
