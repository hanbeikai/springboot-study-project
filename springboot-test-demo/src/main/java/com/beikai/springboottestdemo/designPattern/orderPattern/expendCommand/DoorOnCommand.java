package com.beikai.springboottestdemo.designPattern.orderPattern.expendCommand;

import com.beikai.springboottestdemo.designPattern.orderPattern.basicCommand.Command;
import com.beikai.springboottestdemo.designPattern.orderPattern.model.Door;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/8/19
 * Time: 6:14 下午
 * Description: 拓展命令 - 控制门
 */
public class DoorOnCommand implements Command {
    private Door door;
    public DoorOnCommand(Door door) {
        this.door = door;
    }
    @Override
    public void execute() {
        door.on();
    }

    @Override
    public void undo() {
        door.off();
    }
}
