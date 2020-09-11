package com.beikai.springboottestdemo.designPattern.proxyPattern.netProxyPattern.skeleton;

import com.beikai.springboottestdemo.designPattern.proxyPattern.netProxyPattern.basic.State;
import com.beikai.springboottestdemo.designPattern.proxyPattern.netProxyPattern.stub.GameMachineRemote;
import com.beikai.springboottestdemo.designPattern.proxyPattern.netProxyPattern.state.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/8
 * Time: 6:08 下午
 * Description: 远程服务代理类
 */
public class GameMachineRemoteImpl extends UnicastRemoteObject implements GameMachineRemote {

    private State noQuarterState;
    private State hasQuarterState;
    private State sold;
    private State soldOut;
    private State winnerState;

    private State state = soldOut;
    private int count = 0;
    private String location;

    public GameMachineRemoteImpl(int count, String localtion) throws RemoteException {
        this.noQuarterState = new NoQuarterState(this);
        this.hasQuarterState = new HasQuarterState(this);
        this.sold = new SoldState(this);
        this.soldOut = new SoldOutState(this);
        this.winnerState = new WinnerState(this);
        this.location = localtion;
        if (count > 0) {
            this.count = count;
            this.state = this.noQuarterState;
        }
    }

    // 投入硬币
    public void insertQuarter() throws RemoteException {
        state.insertQuarter();
    }

    // 退回硬币
    public void ejectQuarter() throws RemoteException {
        state.ejectQuarter();
    }

    // 转动曲轴
    public void turnCrank() throws RemoteException {
        // 转动
        if (state.trunCrank()) {
            dispense();
        }
    }

    // 发放糖果
    public void dispense() throws RemoteException {
        // 发放
        state.dispense();
    }

    // 计算库存
    public void releaseBall() throws RemoteException {
        if (this.count > 0) {
            count -= 1;
            System.out.println("一个糖正在发放 ... ");
        }
    }

    @Override
    public String toString() {
        return "AfterGameMachine{" +
                "state=" + state.getClass().getSimpleName() +
                ", count=" + count +
                '}';
    }

    @Override
    public int getCount() throws RemoteException {
        return this.count;
    }

    @Override
    public String getLocation() throws RemoteException {
        return this.location;
    }

    @Override
    public State getState() throws RemoteException {
        return this.state;
    }

    public State getNoQuarterState() throws RemoteException {
        return noQuarterState;
    }

    public void setNoQuarterState(State noQuarterState) throws RemoteException {
        this.noQuarterState = noQuarterState;
    }

    public State getHasQuarterState() throws RemoteException {
        return hasQuarterState;
    }

    public void setHasQuarterState(State hasQuarterState) throws RemoteException {
        this.hasQuarterState = hasQuarterState;
    }

    public State getSold() throws RemoteException {
        return sold;
    }

    public void setSold(State sold) throws RemoteException {
        this.sold = sold;
    }

    public State getSoldOut() throws RemoteException {
        return soldOut;
    }

    public void setSoldOut(State soldOut) throws RemoteException {
        this.soldOut = soldOut;
    }

    public State getWinnerState() throws RemoteException {
        return winnerState;
    }

    public void setWinnerState(State winnerState) throws RemoteException {
        this.winnerState = winnerState;
    }

    public void setState(State state) throws RemoteException {
        this.state = state;
    }

    public void setCount(int count) throws RemoteException {
        this.count = count;
    }

    public void setLocation(String location) throws RemoteException {
        this.location = location;
    }
}
