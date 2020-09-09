package com.beikai.springboottestdemo.designPattern.statePattern;

import com.beikai.springboottestdemo.designPattern.statePattern.after.AfterGameMachine;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/8
 * Time: 12:03 下午
 * Description: 使用 状态模式 后测试demo
 */
public class AfterTestDemo02 {

    public static void main(String[] args) {
        AfterGameMachine gameMachine = new AfterGameMachine(10);
        int temp = gameMachine.getCount();
        for (int i = 0; i < temp; i++) {
            System.out.println("\n-----------< 测试出糖果 >---------\n");
            // 投入硬币
            gameMachine.insertQuarter();
            // 转动曲轴
            gameMachine.turnCrank();
            System.out.println(gameMachine);
        }
    }
}
