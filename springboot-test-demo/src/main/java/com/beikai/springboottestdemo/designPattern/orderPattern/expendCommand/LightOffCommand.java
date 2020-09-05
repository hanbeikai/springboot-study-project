package com.beikai.springboottestdemo.designPattern.orderPattern.expendCommand;

import com.beikai.springboottestdemo.designPattern.orderPattern.basicCommand.Command;
import com.beikai.springboottestdemo.designPattern.orderPattern.model.Light;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/8/19
 * Time: 8:33 上午
 * Description: 拓展命令-灯
 */
public class LightOffCommand implements Command {
    private Light light;
    public LightOffCommand(Light light) {
        this.light = light;
    }
    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}
