package com.beikai.springboottestdemo.designPattern.statePattern.after;

import com.beikai.springboottestdemo.designPattern.statePattern.basic.State;
import com.beikai.springboottestdemo.designPattern.statePattern.state.*;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/8
 * Time: 10:35 上午
 * Description: 使用状态模式 的 糖果机器
 */
@Data
public class AfterGameMachine {

    private State noQuarterState;
    private State hasQuarterState;
    private State sold;
    private State soldOut;
    private State winnerState;

    private State state = soldOut;
    private int count = 0;

    public AfterGameMachine(int count) {
        this.noQuarterState = new NoQuarterState(this);
        this.hasQuarterState = new HasQuarterState(this);
        this.sold = new SoldState(this);
        this.soldOut = new SoldOutState(this);
        this.winnerState = new WinnerState(this);

        if (count > 0){
            this.count = count;
            this.state = this.noQuarterState;
        }
    }

    // 投入硬币
    public void insertQuarter() {
        state.insertQuarter();
    }

    // 退回硬币
    public void ejectQuarter() {
        state.ejectQuarter();
    }

    // 转动曲轴
    public void turnCrank() {
        // 转动
        if (state.trunCrank()) {
            dispense();
        }
    }

    // 发放糖果
    public void dispense() {
        // 发放
        state.dispense();
    }

    // 计算库存
    public void releaseBall(){
        if (this.count > 0){
            count -= 1;
            System.out.println("一个糖正在发放 ... ");
        }
    }

    @Override
    public String toString() {
        return "AfterGameMachine{" +
                "state=" + state.getClass().getSimpleName() +
                ", count=" + count +
                '}';
    }
}
