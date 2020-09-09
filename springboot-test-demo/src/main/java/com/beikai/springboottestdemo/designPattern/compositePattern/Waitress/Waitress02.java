package com.beikai.springboottestdemo.designPattern.compositePattern.Waitress;

import com.beikai.springboottestdemo.designPattern.compositePattern.basic.MenuComponent;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/7
 * Time: 10:29 上午
 * Description: No Description
 */
public class Waitress02 {
    MenuComponent rootMenu;

    public Waitress02(MenuComponent rootMenu) {
        this.rootMenu = rootMenu;
    }

    // 调用根目录的打印选项,根目录会调用子目录的打印
    public void print(){
        rootMenu.print();
    }

    public void print2(){
        Iterator<MenuComponent> iterator =
                rootMenu.createIterator();
        while (iterator.hasNext()) {
            MenuComponent next = iterator.next();
            try {
                if (next.getIsVegetarian()) {
                    next.print();
                }
            } catch (Exception e) {
            }

        }
    }
}
