package com.beikai.springboottestdemo.designPattern.orderPattern;

import com.beikai.springboottestdemo.designPattern.orderPattern.basicCommand.Command;
import com.beikai.springboottestdemo.designPattern.orderPattern.controller.SimpleRemotArrController;
import com.beikai.springboottestdemo.designPattern.orderPattern.expendCommand.*;
import com.beikai.springboottestdemo.designPattern.orderPattern.model.Ceiling;
import com.beikai.springboottestdemo.designPattern.orderPattern.model.Door;
import com.beikai.springboottestdemo.designPattern.orderPattern.model.Light;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/8/19
 * Time: 8:41 上午
 * Description: 简单测试远程命令是否可以
 */
public class SimpleRemoteTestDemo04 {

    public static void main(String[] args) {
        Ceiling ceiling = new Ceiling("卧室风扇");
        // 分别创建 风扇命令 灯光命令 门命令
        CeilingHightCommand ceilingHightCommand = new CeilingHightCommand(ceiling);
        CeilingMOffCommand ceilingMOffCommand = new CeilingMOffCommand(ceiling);

        Light light = new Light();
        LightOnCommand lightOnCommand = new LightOnCommand(light);
        LightOffCommand lightOffCommand = new LightOffCommand(light);

        Door door = new Door();
        DoorOnCommand doorOnCommand = new DoorOnCommand(door);
        DoorOffCommand doorOffCommand = new DoorOffCommand(door);

        // 创建组合命令
        MoreCommand onCommand = new MoreCommand(new Command[]{ceilingHightCommand,lightOnCommand,doorOnCommand});
        MoreCommand offCommand = new MoreCommand(new Command[]{ceilingMOffCommand,lightOffCommand,doorOffCommand});

        // 创建组合控制器
        SimpleRemotArrController simpleRemotArrController = new SimpleRemotArrController();
        simpleRemotArrController.setCommand(0,onCommand,offCommand);

        // 开始执行按钮
        simpleRemotArrController.onButton(0);
        // 撤销按钮
        simpleRemotArrController.undoButton();

    }
}
