package com.beikai.springboottestdemo.designPattern.proxyPattern.netProxyPattern.after;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/8
 * Time: 8:59 下午
 * Description: No Description
 */
public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {

    public MyRemoteImpl() throws RemoteException {
    }

    @Override
    public String say() throws RemoteException {
        return "调用成功了 ... ";
    }
}
