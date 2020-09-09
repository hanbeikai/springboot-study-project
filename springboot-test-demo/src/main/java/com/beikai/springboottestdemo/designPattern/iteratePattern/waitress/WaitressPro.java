package com.beikai.springboottestdemo.designPattern.iteratePattern.waitress;

import com.beikai.springboottestdemo.designPattern.iteratePattern.basic.Menu;
import com.beikai.springboottestdemo.designPattern.iteratePattern.basic.MenuItem;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/6
 * Time: 12:02 上午
 * Description: 女招待 加强版
 */
public class WaitressPro {
    List<Menu> menus;

    public WaitressPro(Menu ... menus) {
        this.menus = Arrays.asList(menus);
    }

    public WaitressPro(List<Menu> menus) {
        this.menus = menus;
    }

    public void addMenu(Menu menu){
        menus.add(menu);
    }

    public void printAll(){
        for (Menu menu : menus) {
            System.out.println("\n------------------------ " + menu.getClass().getSimpleName() );
            print(menu.createIterator());
        }
    }

    private void print(Iterator iterator){
        while (iterator.hasNext()){
            MenuItem next = (MenuItem) iterator.next();
            System.out.println(next);
        }
    }
}
