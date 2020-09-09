package com.beikai.springboottestdemo.designPattern.proxyPattern.state;

import com.beikai.springboottestdemo.designPattern.proxyPattern.skeleton.GameMachineRemoteImpl;
import com.beikai.springboottestdemo.designPattern.proxyPattern.basic.State;

import java.rmi.RemoteException;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/8
 * Time: 10:30 上午
 * Description: No Description
 */
public class NoQuarterState implements State {

    // 为了防止jvm序列化这个变量,使用 transient 修饰
    transient GameMachineRemoteImpl afterGameMachine;

    public NoQuarterState(GameMachineRemoteImpl afterGameMachine) {
        this.afterGameMachine = afterGameMachine;
    }

    @Override
    public void insertQuarter() throws RemoteException {
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
