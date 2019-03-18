package com.beikai.springboottestdemo.test;

/**
 * @ClassName Test01
 * @Description TODO
 * @Author Admin
 * @Date 2019/2/12 23:33
 * @Version 1.0
 **/
public class Test01 {
    public static void main(String[] args) {
        System.out.println(findResult());
    }
    public static Integer findResult(){
        Integer i = 5;
        try {
            return i = 6;
        } catch (Exception e) {
            return i = 7;
        } finally {
            return i = 8;
        }
    }


}
