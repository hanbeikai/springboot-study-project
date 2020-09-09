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
public class SoldOutState implements State {
    private AfterGameMachine afterGameMachine;

    public SoldOutState(AfterGameMachine afterGameMachine) {
        this.afterGameMachine = afterGameMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("已售罄,不要再投币了,请稍后再来 ... ");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("已售罄,不要投币,不要退币 ... ");
    }

    @Override
    public Boolean trunCrank() {
        System.out.println("已售罄,不要转动曲轴 ... ");
        return false;
    }

    @Override
    public void dispense() {
        System.out.println("已售罄,不会发放了 ... ");
    }
}
