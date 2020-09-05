package com.beikai.springboottestdemo.designPattern.orderPattern.controller;

import com.beikai.springboottestdemo.designPattern.orderPattern.basicCommand.Command;
import com.beikai.springboottestdemo.designPattern.orderPattern.expendCommand.NoCommand;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/8/19
 * Time: 8:08 下午
 * Description: 实现控制器组
 */
public class SimpleRemotArrController {

    // 声明命令组
    private Command[] onCommand;
    private Command[] offCommand;
    private final Integer index = 7;

    // 创建用来记录当前命令的命令
    private Command undoCommand;

    // 初始化命令组,并分配给noCommand
    public SimpleRemotArrController(Integer index) {
        initCommand(index);
    }

    /**
     * 功能描述:
     * 〈初始化控制器组〉
     *
     * @param index
     * @return : void
     * @author : hbk
     * @date : 2020/8/20
     */
    private void initCommand(Integer index) {
        onCommand = new Command[index];
        offCommand = new Command[index];
        Command noCommand = new NoCommand();
        for (int i = 0; i < index; i++) {
            onCommand[i] = noCommand;
            offCommand[i] = noCommand;
        }
        // 初始化 undo命令
        undoCommand = noCommand;
    }

    public SimpleRemotArrController() {
        initCommand(index);
    }

    public SimpleRemotArrController setCommand(int index, Command onCommand, Command offCommand) {
        this.onCommand[index] = onCommand;
        this.offCommand[index] = offCommand;
        return this;
    }

    /**
     * 功能描述:
     * 〈获取开命令〉
     *
     * @param index
     * @return : com.beikai.springboottestdemo.designPattern.orderPattern.basicCommand.Command
     * @author : hbk
     * @date : 2020/8/20
     */
    private Command getOnCommand(int index) {
        return getCommand(index, onCommand);
    }

    /**
     * 功能描述:
     * 〈获取命令〉
     *
     * @param index
     * @param onCommand
     * @return : com.beikai.springboottestdemo.designPattern.orderPattern.basicCommand.Command
     * @author : hbk
     * @date : 2020/8/20
     */
    private Command getCommand(int index, Command[] onCommand) {
        if (index < onCommand.length) {
            return onCommand[index];
        }
        System.out.println("超过了控制器控制的数量");
        return new NoCommand();
    }

    /**
     * 功能描述:
     * 〈获取关命令〉
     *
     * @param index
     * @return : com.beikai.springboottestdemo.designPattern.orderPattern.basicCommand.Command
     * @author : hbk
     * @date : 2020/8/20
     */
    private Command getOffCommand(int index) {
        return getCommand(index, offCommand);
    }

    // 开启按钮
    public void onButton(int index) {
        getOnCommand(index).execute();
        // 每一次执行命令的时候,把当前命令放到临时undo命令里,undo命令执行与当前命令相反的操作
        undoCommand = getOnCommand(index);
    }

    // 关闭按钮
    public void offButton(int index) {
        getOffCommand(index).execute();
        undoCommand = getOffCommand(index);
    }

    public void undoButton(){
        undoCommand.undo();
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < onCommand.length; i++) {
            Command onCommandTemp = onCommand[i];
            Command offCommandTemp = offCommand[i];
            stringBuffer.append("onCommand=").append(onCommandTemp.getClass().getName()).append(", index=").append(i).append("     ")
                    .append("offCommand=").append(offCommandTemp.getClass().getName()).append(", index=").append(i).append("\n")
            ;
        }

        return stringBuffer.toString();
    }
}
