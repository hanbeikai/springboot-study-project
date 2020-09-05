package com.beikai.springboottestdemo.designPattern.orderPattern.controller;

import com.beikai.springboottestdemo.designPattern.orderPattern.basicCommand.Command;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/8/19
 * Time: 8:37 上午
 * Description: 命令控制类
 */
public class SimpleRemoteController {
    private Command command;
    public SimpleRemoteController() {
    }
    /**
     * 功能描述:
     * 〈设置命令的方法〉
     *
     * @param command
     * @return : void
     * @author : hbk
     * @date : 2020/8/19
     */
    public SimpleRemoteController setCommand(Command command) {
        this.command = command;
        return this;
    }
    /**
     * 功能描述:
     * 〈创建点击发送按钮〉
     *
     * @return :
     * @author : hbk
     * @date : 2020/8/19
     */
    public void buttonWasPressed(){
        command.execute();
    }
}
