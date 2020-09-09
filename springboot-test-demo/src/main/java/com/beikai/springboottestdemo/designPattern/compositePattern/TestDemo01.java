package com.beikai.springboottestdemo.designPattern.compositePattern;

import com.beikai.springboottestdemo.designPattern.compositePattern.Waitress.Waitress;
import com.beikai.springboottestdemo.designPattern.compositePattern.basic.MenuComponent;
import com.beikai.springboottestdemo.designPattern.compositePattern.menu.Menu;
import com.beikai.springboottestdemo.designPattern.compositePattern.menu.MenuItem;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/7
 * Time: 10:32 上午
 * Description: 组合模式 第一次测试
 */
public class TestDemo01 {

    public static void main(String[] args) {
        MenuComponent panCakeMenu = new Menu("panCake","早餐");
        MenuComponent dinnerMenu = new Menu("dinner","午餐");
        MenuComponent coffee = new Menu("甜点", "甜点");

        MenuComponent allMenu = new Menu("总菜单","总菜单");
        allMenu.add(panCakeMenu);
        allMenu.add(dinnerMenu);
        allMenu.add(coffee);

        // 添加早餐
        MenuComponent pancake = new MenuItem("油条","中华美食",true,2.0);
        panCakeMenu.add(pancake);

        // 添加午餐
        MenuComponent dinner = new MenuItem("牛排","和牛",false,100.0);
        dinnerMenu.add(dinner);

        // 添加甜点
        MenuComponent coffee2 = new MenuItem("猫屎咖啡","推荐",true,20.0);
        coffee.add(coffee2);

        Waitress waitress = new Waitress(allMenu);
        waitress.print();
    }
}
