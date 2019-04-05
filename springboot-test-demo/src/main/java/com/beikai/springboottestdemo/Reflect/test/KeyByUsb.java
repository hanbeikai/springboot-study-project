package com.beikai.springboottestdemo.Reflect.test;

/**
 * @ClassName KeyByUsb
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/5 20:05
 * @Version 1.0
 **/
public class KeyByUsb implements USB{
    @Override
    public void start() {
        System.out.println("添加键盘了...");
    }

    @Override
    public void close() {
        System.out.println("拔掉键盘了...");
    }
}
