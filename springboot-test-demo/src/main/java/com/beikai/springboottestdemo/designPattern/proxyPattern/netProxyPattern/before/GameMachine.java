package com.beikai.springboottestdemo.designPattern.proxyPattern.netProxyPattern.before;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/8
 * Time: 8:23 下午
 * Description: No Description
 */
@Data
public class GameMachine {

    Integer count = 0;
    String localtion;

    public GameMachine(Integer count, String localtion) {
        this.count = count;
        this.localtion = localtion;
    }


}
