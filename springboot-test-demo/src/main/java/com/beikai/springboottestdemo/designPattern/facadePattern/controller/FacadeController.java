package com.beikai.springboottestdemo.designPattern.facadePattern.controller;

import com.beikai.springboottestdemo.designPattern.facadePattern.model.AirCondition;
import com.beikai.springboottestdemo.designPattern.facadePattern.model.Door;
import com.beikai.springboottestdemo.designPattern.facadePattern.model.Light;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/8/20
 * Time: 4:45 下午
 * Description: 外观控制类
 */
public class FacadeController {

    private Light light;
    private Door door;
    private AirCondition airCondition;

    public FacadeController() {
        this.light = new Light();
        this.door = new Door();
        this.airCondition = new AirCondition();
    }

    public FacadeController(Light light, Door door, AirCondition airCondition) {
        this.light = light;
        this.door = door;
        this.airCondition = airCondition;
    }

    public void on(){
        door.on();
        light.on();
        airCondition.on();
    }

    public void off(){
        airCondition.off();
        light.off();
        door.off();
    }
}
