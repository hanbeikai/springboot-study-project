package com.beikai.springboottestdemo.designPattern.iteratePattern;

import com.beikai.springboottestdemo.designPattern.iteratePattern.basic.Menu;
import com.beikai.springboottestdemo.designPattern.iteratePattern.menu.CoffeeMenu;
import com.beikai.springboottestdemo.designPattern.iteratePattern.menu.DinnerMenu;
import com.beikai.springboottestdemo.designPattern.iteratePattern.menu.PancakeMenu;
import com.beikai.springboottestdemo.designPattern.iteratePattern.waitress.WaitressPro;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/5
 * Time: 11:58 下午
 * Description: No Description
 */
public class TestDemo02 {
    public static void main(String[] args) {
        Menu pancakeMenu = new PancakeMenu();
        Menu dinnerMenu = new DinnerMenu();
        Menu coffeeMenu = new CoffeeMenu();

        WaitressPro waitress = new WaitressPro(pancakeMenu,dinnerMenu,coffeeMenu);

        // 打印
        waitress.printAll();
    }
}
