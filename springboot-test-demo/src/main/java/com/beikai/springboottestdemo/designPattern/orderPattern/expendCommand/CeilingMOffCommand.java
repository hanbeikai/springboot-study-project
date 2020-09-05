package com.beikai.springboottestdemo.designPattern.orderPattern.expendCommand;

import com.beikai.springboottestdemo.designPattern.orderPattern.basicCommand.Command;
import com.beikai.springboottestdemo.designPattern.orderPattern.model.Ceiling;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/8/20
 * Time: 1:37 下午
 * Description: 风扇高速命令
 */
public class CeilingMOffCommand implements Command {

    private Ceiling ceiling;
    private Integer speed;

    public CeilingMOffCommand(Ceiling ceiling) {
        this.ceiling = ceiling;
    }

    @Override
    public void execute() {
        // 在执行命令前,先保存之前的转速
        speed = ceiling.getSpeed();
        ceiling.off();
    }

    @Override
    public void undo() {
        if (speed.equals(Ceiling.hight)) {
            ceiling.setHight();
        } else if (speed.equals(Ceiling.mid)) {
            ceiling.setMid();
        } else if (speed.equals(Ceiling.low)) {
            ceiling.setLow();
        } else if (speed.equals(Ceiling.stop)) {
            ceiling.off();
        }
    }
}
