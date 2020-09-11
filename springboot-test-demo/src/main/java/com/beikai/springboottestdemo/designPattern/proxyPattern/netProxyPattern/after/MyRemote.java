package com.beikai.springboottestdemo.designPattern.proxyPattern.netProxyPattern.after;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/8
 * Time: 8:58 下午
 * Description: No Description
 */
public interface MyRemote extends Remote {

    public String say() throws RemoteException;
}
