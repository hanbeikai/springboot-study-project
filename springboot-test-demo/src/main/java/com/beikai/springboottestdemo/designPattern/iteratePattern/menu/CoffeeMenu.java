package com.beikai.springboottestdemo.designPattern.iteratePattern.menu;

import com.beikai.springboottestdemo.designPattern.iteratePattern.basic.Menu;
import com.beikai.springboottestdemo.designPattern.iteratePattern.basic.MenuItem;

import java.util.Hashtable;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/7
 * Time: 9:37 上午
 * Description: No Description
 */
public class CoffeeMenu implements Menu {

    Hashtable hashtable;

    // 构造 菜单
    public CoffeeMenu() {
        hashtable = new Hashtable();
        addItem("猫屎咖啡","推荐",true,20.0);
        addItem("拿铁","可以",true,20.0);
        addItem("烧仙草","推荐",true,20.0);
        addItem("铁观音","推荐",true,20.0);
        addItem("红茶","推荐",true,20.0);
    }

    // 添加 菜品
    public void addItem(String name,String description,Boolean isVegetarian,Double price){
        hashtable.put(name,new MenuItem(name,description,isVegetarian,price));
    }

    @Override
    public Iterator createIterator() {
        return hashtable.values().iterator();
    }
}
