package com.beikai.springboottestdemo.designPattern.orderPattern.expendCommand;

import com.beikai.springboottestdemo.designPattern.orderPattern.basicCommand.Command;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/8/20
 * Time: 9:36 上午
 * Description: No Description
 */
public class NoCommand implements Command {
    @Override
    public void execute() {
    }

    @Override
    public void undo() {

    }
}
