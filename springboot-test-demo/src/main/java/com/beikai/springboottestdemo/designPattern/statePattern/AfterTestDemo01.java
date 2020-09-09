package com.beikai.springboottestdemo.designPattern.statePattern;

import com.beikai.springboottestdemo.designPattern.statePattern.after.AfterGameMachine;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/8
 * Time: 12:03 下午
 * Description: 使用 状态模式 后测试demo
 */
public class AfterTestDemo01 {

    public static void main(String[] args) {
        AfterGameMachine gameMachine = new AfterGameMachine(5);

        gameMachine.turnCrank();

        System.out.println("\n-----------< 测试出糖果 >---------\n");
        // 投入硬币
        gameMachine.insertQuarter();
        // 转动曲轴
        gameMachine.turnCrank();
        System.out.println(gameMachine);

        System.out.println("\n-----------< 测试退币 >---------\n");
        // 投入硬币
        gameMachine.insertQuarter();
        // 退币
        gameMachine.ejectQuarter();
        // 转动曲轴
        gameMachine.turnCrank();
        System.out.println(gameMachine);

        System.out.println("\n-----------< 测试退币 >---------\n");
        // 投入硬币
        gameMachine.insertQuarter();
        // 转动曲轴
        gameMachine.turnCrank();
        // 投入硬币
        gameMachine.insertQuarter();
        // 转动曲轴
        gameMachine.turnCrank();
        // 弹出硬币
        gameMachine.ejectQuarter();

        System.out.println(gameMachine);

        System.out.println("\n-----------< 测试压力 >---------\n");

        // 投入硬币
        gameMachine.insertQuarter();
        gameMachine.insertQuarter();
        // 转动曲轴
        gameMachine.turnCrank();
        gameMachine.insertQuarter();
        // 转动曲轴
        gameMachine.turnCrank();
        gameMachine.insertQuarter();
        // 转动曲轴
        gameMachine.turnCrank();

        System.out.println(gameMachine);
    }
}
