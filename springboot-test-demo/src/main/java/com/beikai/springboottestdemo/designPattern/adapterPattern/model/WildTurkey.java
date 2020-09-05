package com.beikai.springboottestdemo.designPattern.adapterPattern.model;

import com.beikai.springboottestdemo.designPattern.adapterPattern.basicInterface.Turkey;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/8/20
 * Time: 3:20 下午
 * Description: No Description
 */
public class WildTurkey implements Turkey {
    @Override
    public void gobble() {
        System.out.println("gobble ... ");
    }

    @Override
    public void fly() {
        System.out.println("fly ... ");
    }
}
