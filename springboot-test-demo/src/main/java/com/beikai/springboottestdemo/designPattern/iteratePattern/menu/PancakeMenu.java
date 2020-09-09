package com.beikai.springboottestdemo.designPattern.iteratePattern.menu;

import com.beikai.springboottestdemo.designPattern.iteratePattern.basic.Menu;
import com.beikai.springboottestdemo.designPattern.iteratePattern.basic.MenuItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/5
 * Time: 11:45 下午
 * Description: No Description
 */
public class PancakeMenu implements Menu {

    List<MenuItem> menuItems;

    public PancakeMenu() {
        this.menuItems = new ArrayList<>();
        //  初始化 早餐菜单
        addItem("油条","中华美食",true,2.0);
        addItem("豆浆","中华美食",true,3.0);
        addItem("小笼包","中华美食",false,21.0);
        addItem("水煎包(素)","中华美食",true,3.0);
        addItem("水煎包(荤)","中华美食",false,5.0);
    }

    public void addItem(String name,String description,Boolean isVegetarian,Double price){
        menuItems.add(new MenuItem(name,description,isVegetarian,price));
    }

    @Override
    public Iterator createIterator() {
        // 使用 list 自带的 迭代器
        return menuItems.iterator();
    }
}
