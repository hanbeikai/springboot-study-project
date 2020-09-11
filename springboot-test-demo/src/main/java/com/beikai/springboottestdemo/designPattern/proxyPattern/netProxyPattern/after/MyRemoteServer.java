package com.beikai.springboottestdemo.designPattern.proxyPattern.netProxyPattern.after;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/8
 * Time: 10:57 下午
 * Description: 远程服务 服务端
 */
public class MyRemoteServer {
    public static void main(String[] args) {

        try {
            // 启动一个注册表 并绑定端口
            Registry registry = LocateRegistry.createRegistry(8888);

            // 注册服务对象
            MyRemote myRemote = new MyRemoteImpl();
            registry.rebind("myRemote", myRemote);
            System.out.println("server binding ... ");

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
