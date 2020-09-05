package com.beikai.springboottestdemo.designPattern.facadePattern.model;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/8/20
 * Time: 4:42 下午
 * Description: No Description
 */
public class Door {

    public void on(){
        System.out.println("门开了 ... ");
    }

    public void off(){
        System.out.println("门关了 ... ");
    }

    public String getName(){
        return "空调";
    }
}
