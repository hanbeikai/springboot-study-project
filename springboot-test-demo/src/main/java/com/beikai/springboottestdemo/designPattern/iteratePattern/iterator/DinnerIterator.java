package com.beikai.springboottestdemo.designPattern.iteratePattern.iterator;

import com.beikai.springboottestdemo.designPattern.iteratePattern.basic.MenuItem;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/5
 * Time: 6:08 下午
 * Description: 早餐 自定义迭代器
 */
public class DinnerIterator implements Iterator {
    MenuItem[] menuItems;
    int position = 0;

    public DinnerIterator(MenuItem[] menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public boolean hasNext() {
        if (menuItems.length > position && menuItems[position] != null ) {
            return true;
        }
        return false;
    }

    @Override
    public Object next() {
        MenuItem menuItem = menuItems[position];
        position += 1;
        return menuItem;
    }

    @Override
    public void remove() {

        if (position <= 0) {
            throw new IllegalStateException("在至少完成一个next（）之前，不能删除项");
        }

        // 要删除的项之后的所有的项向前移动一步,最后一个设置为null
        if (menuItems[position-1] != null) {
            for (int i = position - 1; i < menuItems.length - 1; i++) {
                menuItems[i] = menuItems[i+1];
            }
            menuItems[menuItems.length - 1]= null;
        }

    }
}
