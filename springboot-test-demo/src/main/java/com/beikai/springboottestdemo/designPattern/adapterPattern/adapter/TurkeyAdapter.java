package com.beikai.springboottestdemo.designPattern.adapterPattern.adapter;

import com.beikai.springboottestdemo.designPattern.adapterPattern.basicInterface.Duck;
import com.beikai.springboottestdemo.designPattern.adapterPattern.basicInterface.Turkey;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/8/20
 * Time: 3:21 下午
 * Description: 创建火鸡适配器
 */
public class TurkeyAdapter implements Duck {
    private Turkey turkey;
    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }
    @Override
    public void quack() {
        turkey.gobble();
    }
    @Override
    public void fly() {
        for (int i = 0; i < 5; i++) {
             turkey.fly();
        }
    }
}
