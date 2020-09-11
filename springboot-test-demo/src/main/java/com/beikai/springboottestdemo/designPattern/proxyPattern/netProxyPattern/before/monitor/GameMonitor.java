package com.beikai.springboottestdemo.designPattern.proxyPattern.netProxyPattern.before.monitor;

import com.beikai.springboottestdemo.designPattern.proxyPattern.netProxyPattern.before.GameMachine;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/8
 * Time: 8:26 下午
 * Description: No Description
 */
public class GameMonitor {
    GameMachine gameMachine;

    public GameMonitor(GameMachine gameMachine) {
        this.gameMachine = gameMachine;
    }

    public void report(){
        System.out.println("当前地址为 : " + gameMachine.getLocaltion());
        System.out.println("当前数字是 : " + gameMachine.getCount());
    }
}
