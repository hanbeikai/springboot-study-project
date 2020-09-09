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
public class SoldState implements State {

    // 为了防止jvm序列化这个变量,使用 transient 修饰
    transient GameMachineRemoteImpl afterGameMachine;

    public SoldState(GameMachineRemoteImpl afterGameMachine) {
        this.afterGameMachine = afterGameMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("请稍后 ... ");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("商品正在发放,无法退款 ... ");
    }

    @Override
    public Boolean trunCrank() {
        System.out.println("就算您转了两次曲轴,也不会给您发放两个糖果 ... ");
        return false;
    }

    @Override
    public void dispense() throws RemoteException {
        afterGameMachine.releaseBall();
        if (afterGameMachine.getCount() <= 0) {
            // 如果库存完了 ,设置为 已售罄
            System.out.println("糖果已经售完 ... ");
            afterGameMachine.setState(afterGameMachine.getSoldOut());
        }else {
            // 如果库存还有, 设置为没有投币状态
            afterGameMachine.setState(afterGameMachine.getNoQuarterState());
        }
    }
}
