package com.beikai.springboottestdemo.designPattern.proxyPattern.netProxyPattern;

import com.beikai.springboottestdemo.designPattern.proxyPattern.netProxyPattern.proxy.GameProxy;
import com.beikai.springboottestdemo.designPattern.proxyPattern.netProxyPattern.stub.GameMachineRemote;

import java.rmi.NotBoundException;
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
public class ProxyGameMachineTestDemoClient01 {
    public static void main(String[] args) throws RemoteException {
        new ProxyGameMachineTestDemoClient01().go();
    }

    private void go() throws RemoteException {
        Registry registry = LocateRegistry.getRegistry("127.0.0.1",8889);

        String[] list = registry.list();
        for (String s : list) {
            System.out.println("服务的对象 : " + s);
        }

        try {
            GameMachineRemote gameMachineRemote = (GameMachineRemote) registry.lookup("gameMachineRemote");
            GameProxy proxy = new GameProxy(gameMachineRemote);
            proxy.report();
            System.out.println("链接上了远程服务 ... ");
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

    }
}
