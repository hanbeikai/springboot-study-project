package com.beikai.springboottestdemo.designPattern.statePattern.basic;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/8
 * Time: 9:35 上午
 * Description: 状态模式-基本类
 */
public interface State {

    void insertQuarter();

    void ejectQuarter();

    Boolean trunCrank();

    void dispense();

}
