package com.beikai.springboottestdemo.designPattern.proxyPattern.state;

import com.beikai.springboottestdemo.designPattern.proxyPattern.basic.State;
import com.beikai.springboottestdemo.designPattern.proxyPattern.skeleton.GameMachineRemoteImpl;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/8
 * Time: 10:30 上午
 * Description: No Description
 */
public class SoldOutState implements State {
    // 为了防止jvm序列化这个变量,使用 transient 修饰
    transient GameMachineRemoteImpl afterGameMachine;

    public SoldOutState(GameMachineRemoteImpl afterGameMachine) {
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
