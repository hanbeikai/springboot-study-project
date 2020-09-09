package com.beikai.springboottestdemo.designPattern.proxyPattern.stub;

import com.beikai.springboottestdemo.designPattern.proxyPattern.basic.State;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/8
 * Time: 6:03 下午
 * Description: 糖果机 远程 服务接口
 */
public interface GameMachineRemote extends Remote {
    public int getCount() throws RemoteException;
    public String getLocation() throws RemoteException;
    public State getState() throws RemoteException;
}
