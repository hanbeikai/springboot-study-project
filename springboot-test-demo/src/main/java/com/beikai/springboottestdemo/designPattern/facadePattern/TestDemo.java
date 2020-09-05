package com.beikai.springboottestdemo.designPattern.facadePattern;

import com.beikai.springboottestdemo.designPattern.facadePattern.controller.FacadeController;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/8/20
 * Time: 4:48 下午
 * Description: 外观模式测试demo
 */
public class TestDemo {

    public static void main(String[] args) {
        FacadeController facadeController = new FacadeController();
        facadeController.on();
        facadeController.off();
    }
}
