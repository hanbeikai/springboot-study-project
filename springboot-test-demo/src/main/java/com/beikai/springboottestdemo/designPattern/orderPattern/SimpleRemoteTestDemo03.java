package com.beikai.springboottestdemo.designPattern.orderPattern;

import com.beikai.springboottestdemo.designPattern.orderPattern.controller.SimpleRemotArrController;
import com.beikai.springboottestdemo.designPattern.orderPattern.expendCommand.CeilingHightCommand;
import com.beikai.springboottestdemo.designPattern.orderPattern.expendCommand.CeilingLowCommand;
import com.beikai.springboottestdemo.designPattern.orderPattern.expendCommand.CeilingMOffCommand;
import com.beikai.springboottestdemo.designPattern.orderPattern.expendCommand.CeilingMidCommand;
import com.beikai.springboottestdemo.designPattern.orderPattern.model.Ceiling;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/8/19
 * Time: 8:41 上午
 * Description: 简单测试远程命令是否可以
 */
public class SimpleRemoteTestDemo03 {

    public static void main(String[] args) {
        Ceiling ceiling = new Ceiling("卧室风扇");
        // 分别创建 风扇 高 中 低 关闭命令
        CeilingHightCommand ceilingHightCommand = new CeilingHightCommand(ceiling);
        CeilingMidCommand ceilingMidCommand = new CeilingMidCommand(ceiling);
        CeilingLowCommand ceilingLowCommand = new CeilingLowCommand(ceiling);
        CeilingMOffCommand ceilingMOffCommand = new CeilingMOffCommand(ceiling);

        // 创建并初始化组控制器
        SimpleRemotArrController simpleRemotArrController = new SimpleRemotArrController(3);
        simpleRemotArrController.setCommand(0,ceilingLowCommand,ceilingMOffCommand);
        simpleRemotArrController.setCommand(1,ceilingMidCommand,ceilingMOffCommand);
        simpleRemotArrController.setCommand(2,ceilingHightCommand,ceilingMOffCommand);


        // 分别执行不同的命令
        simpleRemotArrController.onButton(0);
        simpleRemotArrController.offButton(0);
        System.out.println(simpleRemotArrController);
        simpleRemotArrController.undoButton();

        simpleRemotArrController.onButton(2);
        System.out.println(simpleRemotArrController);
        simpleRemotArrController.undoButton();
    }
}
