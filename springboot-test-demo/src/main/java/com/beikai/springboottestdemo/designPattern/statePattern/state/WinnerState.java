package com.beikai.springboottestdemo.designPattern.statePattern.state;

import com.beikai.springboottestdemo.designPattern.statePattern.after.AfterGameMachine;
import com.beikai.springboottestdemo.designPattern.statePattern.basic.State;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/8
 * Time: 2:51 下午
 * Description: 添加 赢的人的 状态
 */
public class WinnerState implements State {

    private AfterGameMachine afterGameMachine;

    public WinnerState(AfterGameMachine afterGameMachine) {
        this.afterGameMachine = afterGameMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("这个不重要,不会触发");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("这个也不重要,也不会触发 ... ");
    }

    @Override
    public Boolean trunCrank() {
        return false;
    }

    @Override
    public void dispense() {
        // 根据10 % 的概率给赢得人发两个糖果
        System.out.println("恭喜您,获取到10%概率的奖品 ... ");
        afterGameMachine.releaseBall();
        if (afterGameMachine.getCount() <= 0) {
            // 如果库存完了 ,设置为 已售罄
            System.out.println("糖果已经售完 ... ");
            afterGameMachine.setState(afterGameMachine.getSoldOut());
        }else {
            // 此时说明还有库存 减库存
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
}
