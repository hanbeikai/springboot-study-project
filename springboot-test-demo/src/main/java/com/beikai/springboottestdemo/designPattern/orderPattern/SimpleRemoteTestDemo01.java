package com.beikai.springboottestdemo.designPattern.orderPattern;

import com.beikai.springboottestdemo.designPattern.orderPattern.controller.SimpleRemoteController;
import com.beikai.springboottestdemo.designPattern.orderPattern.expendCommand.DoorOffCommand;
import com.beikai.springboottestdemo.designPattern.orderPattern.expendCommand.DoorOnCommand;
import com.beikai.springboottestdemo.designPattern.orderPattern.expendCommand.LightOffCommand;
import com.beikai.springboottestdemo.designPattern.orderPattern.expendCommand.LightOnCommand;
import com.beikai.springboottestdemo.designPattern.orderPattern.model.Door;
import com.beikai.springboottestdemo.designPattern.orderPattern.model.Light;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/8/19
 * Time: 8:41 上午
 * Description: 简单测试远程命令是否可以
 */
public class SimpleRemoteTestDemo01 {
    public static void main(String[] args) {
        // 创建命令要控制的对象
        Light light = new Light();
        // 创建命令控制对象
        LightOnCommand lightCommand = new LightOnCommand(light);
        LightOffCommand lightOffCommand = new LightOffCommand(light);
        // 创建门 命令
        Door door = new Door();
        DoorOnCommand doorCommand = new DoorOnCommand(door);
        DoorOffCommand doorOffCommand = new DoorOffCommand(door);
        // 创建执行对象
        SimpleRemoteController simpleRemoteController = new SimpleRemoteController();
        // 执行对象设置命令
        simpleRemoteController.setCommand(lightCommand).buttonWasPressed();
        // 按下关闭按钮
        simpleRemoteController.setCommand(lightOffCommand).buttonWasPressed();

        // 设置门 命令
        simpleRemoteController.setCommand(doorCommand).buttonWasPressed();
        simpleRemoteController.setCommand(doorOffCommand).buttonWasPressed();
    }
}
