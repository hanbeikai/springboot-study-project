package com.beikai.springboottestdemo.designPattern.adapterPattern.model;

import com.beikai.springboottestdemo.designPattern.adapterPattern.basicInterface.Duck;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/8/20
 * Time: 3:18 下午
 * Description: No Description
 */
public class MallardDuck implements Duck {

    @Override
    public void quack() {
        System.out.println("quack");
    }

    @Override
    public void fly() {
        System.out.println("duck fly");
    }
}
