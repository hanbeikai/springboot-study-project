package com.beikai.springboottestdemo.designPattern.proxyPattern.netProxyPattern.proxy;

import com.beikai.springboottestdemo.designPattern.proxyPattern.netProxyPattern.stub.GameMachineRemote;

import java.rmi.RemoteException;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/8
 * Time: 11:19 下午
 * Description: No Description
 */
public class GameProxy {

    GameMachineRemote gameMachineRemote;

    public GameProxy(GameMachineRemote gameMachineRemote) {
        this.gameMachineRemote = gameMachineRemote;
    }

    public void report(){
        try {
            System.out.println("当前的位置是 : " + gameMachineRemote.getLocation());
            System.out.println("当前的数量是 : " + gameMachineRemote.getCount());
            System.out.println("当前的状态是 : " + gameMachineRemote.getState().getClass().getSimpleName());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
