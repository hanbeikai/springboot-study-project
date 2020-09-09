package com.beikai.springboottestdemo.designPattern.compositePattern.Waitress;

import com.beikai.springboottestdemo.designPattern.compositePattern.basic.MenuComponent;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/7
 * Time: 10:29 上午
 * Description: No Description
 */
public class Waitress {
    MenuComponent rootMenu;

    public Waitress(MenuComponent rootMenu) {
        this.rootMenu = rootMenu;
    }

    // 调用根目录的打印选项,根目录会调用子目录的打印
    public void print(){
        rootMenu.print();
    }
}
