package com.beikai.springboottestdemo.designPattern.adapterPattern;

import com.beikai.springboottestdemo.designPattern.adapterPattern.adapter.TurkeyAdapter;
import com.beikai.springboottestdemo.designPattern.adapterPattern.basicInterface.Duck;
import com.beikai.springboottestdemo.designPattern.adapterPattern.model.MallardDuck;
import com.beikai.springboottestdemo.designPattern.adapterPattern.model.WildTurkey;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/8/20
 * Time: 3:16 下午
 * Description: No Description
 */
public class TestDemo {
    public static void main(String[] args) {
        // 创建火鸡对象
        WildTurkey wildTurkey = new WildTurkey();

        // 创建适配器
        TurkeyAdapter turkeyAdapter = new TurkeyAdapter(wildTurkey);
        System.out.println("turkey info ... ");
        turkeyAdapter.quack();
        turkeyAdapter.fly();

        System.out.println("duck info ... ");
        MallardDuck mallardDuck = new MallardDuck();
        printDuck(mallardDuck);

        System.out.println("adapter info ... ");
        printDuck(turkeyAdapter);
    }

    private static void printDuck(Duck mallardDuck) {
        mallardDuck.quack();
        mallardDuck.fly();
    }
}
