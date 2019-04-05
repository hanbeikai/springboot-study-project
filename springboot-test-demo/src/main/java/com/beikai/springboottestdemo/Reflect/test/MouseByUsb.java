package com.beikai.springboottestdemo.Reflect.test;

/**
 * @ClassName MouseByUsb
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/5 19:19
 * @Version 1.0
 *
 * 鼠标对象
 **/
public class MouseByUsb implements USB{
    @Override
    public void start() {
        System.out.println("添加鼠标...");
    }

    @Override
    public void close() {
        System.out.println("拔掉鼠标...");
    }
}
