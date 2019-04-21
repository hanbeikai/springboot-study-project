package com.beikai.springboottestdemo.test;

/**
 * @ClassName Test07
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/15 22:01
 * @Version 1.0
 **/
public class Test07 {
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = s1;
        String s3 = "abc";
        String s4 = new String("abc");
        String s5 = new String("abc");

        System.out.println(s1 == s2);  //true
        System.out.println(s1 == s3);  // true
        System.out.println(s4 == s5);  // false
        System.out.println(s1 == s4);  // false
        System.out.println(s4.equals(s5)); // true
        System.out.println(s1.equals(s4)); // true
        System.out.println(s1.equals(s3)); // true
    }
}
