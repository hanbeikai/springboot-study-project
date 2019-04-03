package com.beikai.springboottestdemo.IO.util;

/**
 * @ClassName AddressUtils
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/3 21:28
 * @Version 1.0
 **/
public class AddressUtils {

    public static String address = "E:\\test\\abc";

    public static String getAddress(int num){
        return address+num+".java";
    }

    public static String getFilePath(int num){

        if (num > 0){
            return address+num;
        }

        return address;
    }
}
