package com.beikai.springboottestdemo.designPattern.proxyPattern.after;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/8
 * Time: 9:03 下午
 * Description: 远程客户端
 */
public class MyRemoteClient {
    public static void main(String[] args) {
        new MyRemoteClient().go();
    }

    private void go() {
        try {
            // 获取注册表
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 8888);
            String[] list = registry.list();
            for (String s : list) {
                System.out.println("服务对象有 : " + s);
            }

            // 获取指定服务名的对象
            MyRemote myRemote = (MyRemote) registry.lookup("myRemote");
            String say = myRemote.say();
            System.out.println(say);

        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
