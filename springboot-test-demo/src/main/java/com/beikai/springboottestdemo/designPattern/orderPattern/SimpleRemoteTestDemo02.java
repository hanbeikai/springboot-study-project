package com.beikai.springboottestdemo.designPattern.orderPattern;

import com.beikai.springboottestdemo.designPattern.orderPattern.controller.SimpleRemotArrController;
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
public class SimpleRemoteTestDemo02 {

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

        // 组装命令
        SimpleRemotArrController simpleRemotArrController = new SimpleRemotArrController(3);
        simpleRemotArrController.setCommand(0,lightCommand,lightOffCommand);
        simpleRemotArrController.setCommand(1,doorCommand,doorOffCommand);

        // 打印控制器
        System.out.println(simpleRemotArrController);

        // 执行命令
        simpleRemotArrController.onButton(1);
        simpleRemotArrController.undoButton();
        simpleRemotArrController.onButton(0);
        simpleRemotArrController.undoButton();

        simpleRemotArrController.offButton(0);
        simpleRemotArrController.undoButton();
        simpleRemotArrController.offButton(1);
        simpleRemotArrController.undoButton();
    }
}
