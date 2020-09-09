package com.beikai.springboottestdemo.designPattern.iteratePattern.iterator;

import com.beikai.springboottestdemo.designPattern.iteratePattern.basic.MenuItem;

import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/5
 * Time: 6:02 下午
 * Description: 午餐 自定义迭代了
 */
public class PancakeIterator implements Iterator {

    List<MenuItem> menuItems;
    Integer position = 0;

    public PancakeIterator(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public boolean hasNext() {
        // position 小于 列表的长度,并且当前的不为null
        if (menuItems.size() > position && menuItems.get(position) != null) {
            return true;
        }
        return false;
    }

    @Override
    public Object next() {
        MenuItem menuItem = menuItems.get(position);
        position++;
        return menuItem;
    }

    @Override
    public void remove() {
        if (position <= 0) {
            throw new IllegalStateException("不能删除,直到你至少执行一次next()");
        }
        // 调用 list 自带的 删除
        if (menuItems.get(position - 1) != null) {
            menuItems.remove(position - 1);
        }
    }
}
