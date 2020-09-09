package com.beikai.springboottestdemo.designPattern.iteratePattern.waitress;

import com.beikai.springboottestdemo.designPattern.iteratePattern.basic.Menu;
import com.beikai.springboottestdemo.designPattern.iteratePattern.basic.MenuItem;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/5
 * Time: 5:57 下午
 * Description: 女招待 类
 */
public class Waitress {

    // 菜单变量
    Menu pancakeMenu;
    Menu dinnerMenu;
    // 构造
    public Waitress(Menu pancakeMenu, Menu dinnerMenu) {
        this.pancakeMenu = pancakeMenu;
        this.dinnerMenu = dinnerMenu;
    }

    // 打印菜单
    public void printAll(){
        // 打印早餐
        System.out.println("打印早餐  --- ");
        print(pancakeMenu.createIterator());
        // 打印晚餐
        System.out.println("\n打印午餐 --- ");
        print(dinnerMenu.createIterator());
    }

    // 打印
    private void print(Iterator iterator){
        while (iterator.hasNext()){
            MenuItem next = (MenuItem) iterator.next();
            System.out.println(next);
        }
    }
}
