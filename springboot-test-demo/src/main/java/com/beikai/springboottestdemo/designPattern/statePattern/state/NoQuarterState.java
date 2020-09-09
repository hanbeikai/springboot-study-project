package com.beikai.springboottestdemo.designPattern.statePattern.state;

import com.beikai.springboottestdemo.designPattern.statePattern.after.AfterGameMachine;
import com.beikai.springboottestdemo.designPattern.statePattern.basic.State;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/8
 * Time: 10:30 上午
 * Description: No Description
 */
public class NoQuarterState implements State {

    private AfterGameMachine afterGameMachine;

    public NoQuarterState(AfterGameMachine afterGameMachine) {
        this.afterGameMachine = afterGameMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("接受到一个硬币 ...  ");
        // 设置为已有硬币的状态
        afterGameMachine.setState(afterGameMachine.getHasQuarterState());
    }

    @Override
    public void ejectQuarter() {
        System.out.println("您还没有投币 ... ");
    }

    @Override
    public Boolean trunCrank() {
        System.out.println("虽然您转动了曲轴,但是没有糖果,因为您还没有投币 ... ");
        return false;
    }

    @Override
    public void dispense() {
        System.out.println("您还没有投币,无法发放糖果 ... ");
    }
}
