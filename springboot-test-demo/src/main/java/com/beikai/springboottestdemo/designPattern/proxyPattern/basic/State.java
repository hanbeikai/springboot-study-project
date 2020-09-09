package com.beikai.springboottestdemo.designPattern.proxyPattern.basic;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/8
 * Time: 9:35 上午
 * Description: 状态模式-基本类
 */
public interface State extends Serializable {

    void insertQuarter() throws RemoteException;

    void ejectQuarter() throws RemoteException;

    Boolean trunCrank() throws RemoteException;

    void dispense() throws RemoteException;

}
