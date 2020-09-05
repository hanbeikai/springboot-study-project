package com.beikai.springboottestdemo.designPattern.orderPattern.basicCommand;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/8/19
 * Time: 8:29 上午
 * Description: 基础命令类,所有的命令都继承这个类
 */
public interface Command {
    /**
     * 功能描述:
     * 〈执行操作〉
     *
     * @param
     * @return : void
     * @author : hbk
     * @date : 2020/8/19
     */
    public void execute();

    /**
     * 功能描述:
     * 〈实现撤销命令〉
     *
     * @return :
     * @author : hbk
     * @date : 2020/8/20
     */
    public void undo();
}
