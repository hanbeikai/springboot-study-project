package com.beikai.springboottestdemo.designPattern.statePattern.state;

import com.beikai.springboottestdemo.designPattern.statePattern.after.AfterGameMachine;
import com.beikai.springboottestdemo.designPattern.statePattern.basic.State;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/8
 * Time: 10:30 上午
 * Description: No Description
 */
public class HasQuarterState implements State {

    private AfterGameMachine afterGameMachine;

    Random random = new Random();

    public HasQuarterState(AfterGameMachine afterGameMachine) {
        this.afterGameMachine = afterGameMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("已经投了一个币,请不要重复投");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("正在给您退币 ... ");
        afterGameMachine.setState(afterGameMachine.getNoQuarterState());
    }

    @Override
    public Boolean trunCrank() {
        System.out.println("转动曲轴,正在给您准备糖果 ... ");
        if (afterGameMachine.getCount() > 0) {
            // 添加 10% 概率获取 两个糖果的代码
            int randomNum = random.nextInt(10);
            // 10% 的概率以及 当前库存 超过 1 个
            if (randomNum == 0 && afterGameMachine.getCount() > 1) {
                afterGameMachine.setState(afterGameMachine.getWinnerState());
            }else {
                // 在转动之前先判断是否库存,如果没有库存,则不会改变状态
                afterGameMachine.setState(afterGameMachine.getSold());
            }
            return true;
        }
        return false;
    }

    @Override
    public void dispense() {
        System.out.println("现在发放不了糖果 ... ");
    }
}
