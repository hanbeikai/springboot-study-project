package com.beikai.springboottestdemo.designPattern.facadePattern;

import com.beikai.springboottestdemo.designPattern.facadePattern.model.AirCondition;
import com.beikai.springboottestdemo.designPattern.facadePattern.model.Door;
import com.beikai.springboottestdemo.designPattern.facadePattern.model.Light;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/8/20
 * Time: 4:48 下午
 * Description: 外观模式测试demo
 */
public class TestDemo2 {
    // 4 对象的组件
    private Light light = new Light();

    public TestDemo2() {
    }
    public String getName(){
        return "测试dmo";
    }
    public void demo(Door door){
        // 3此方法所创建或实例化的任何对象
        AirCondition airCondition = new AirCondition();
        airCondition.on();

        // 4 对象的组件的方法
        light.on();

        // 2 被当做对象本身方法的参数而传递进来的对象
        door.on();

        // 1. 对象本身的方法
        System.out.println(this.getName());
    }
}
