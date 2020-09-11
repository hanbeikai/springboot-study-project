package com.beikai.springboottestdemo.designPattern.proxyPattern.netProxyPattern;

import com.beikai.springboottestdemo.designPattern.proxyPattern.netProxyPattern.skeleton.GameMachineRemoteImpl;
import com.beikai.springboottestdemo.designPattern.proxyPattern.netProxyPattern.stub.GameMachineRemote;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/8
 * Time: 6:24 下午
 * Description: No Description
 */
public class ProxyGameMachineTestDemoServer01 {
    public static void main(String[] args) throws RemoteException {
        new ProxyGameMachineTestDemoServer01().go();
    }

    private void go() throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(8889);

        GameMachineRemote gameMachineRemote = new GameMachineRemoteImpl(100,this.getClass().getSimpleName());
        // 注册rmi 服务
        registry.rebind("gameMachineRemote",gameMachineRemote);

        System.out.println("服务启动了 ... ");

    }
}
