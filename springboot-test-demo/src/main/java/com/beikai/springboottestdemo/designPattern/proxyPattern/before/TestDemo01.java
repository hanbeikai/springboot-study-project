package com.beikai.springboottestdemo.designPattern.proxyPattern.before;

import com.beikai.springboottestdemo.designPattern.proxyPattern.before.monitor.GameMonitor;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/8
 * Time: 8:27 下午
 * Description: No Description
 */
public class TestDemo01 {

    public static void main(String[] args) {
        int count = 0;

        if (args.length < 2){
            System.out.println("测试 demo  <name> <count>");
            // 终止当前虚拟机
            System.exit(1);
        }

        int i = Integer.parseInt(args[1]);
        GameMachine gameMachine = new GameMachine(i,args[0]);

        GameMonitor gameMonitor = new GameMonitor(gameMachine);

        gameMonitor.report();
    }
}
