package com.beikai.springboottestdemo.designPattern.iteratePattern.menu;

import com.beikai.springboottestdemo.designPattern.iteratePattern.basic.Menu;
import com.beikai.springboottestdemo.designPattern.iteratePattern.basic.MenuItem;
import com.beikai.springboottestdemo.designPattern.iteratePattern.iterator.DinnerIterator;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/5
 * Time: 11:27 下午
 * Description: No Description
 */
public class DinnerMenu implements Menu {

    static final Integer INIT_SIZE = 6;
    MenuItem[] menuItems;
    Integer currArrSize = 0;


    // 构造

    public DinnerMenu() {
        menuItems = new MenuItem[INIT_SIZE];

        // 初始化菜单
        addItem("牛排","和牛",false,100.0);
        addItem("羊排","新疆山羊",false,200.0);
        addItem(" 小青菜","上海青",true,10.0);
        addItem("干煸四季豆","美味四季豆",true,20.0);
        addItem("酸辣土豆丝","店长强烈推荐",true,22.0);
    }


    // 添加菜单项
    public void addItem(String name,String description,Boolean isVegetarian,Double price){
        if (currArrSize >= INIT_SIZE) {
            throw new IndexOutOfBoundsException("当前菜单长度已经超过最大菜单长度!");
        }
        menuItems[currArrSize] = new MenuItem(name,description,isVegetarian,price);
        currArrSize ++;
    }


    @Override
    public Iterator createIterator() {
        return new DinnerIterator(menuItems);
    }
}
