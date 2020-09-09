package com.beikai.springboottestdemo.designPattern.iteratePattern.iterator;

import java.util.Hashtable;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/7
 * Time: 9:31 上午
 * Description: 咖啡迭代器
 */
public class CoffeeIterator implements Iterator {

    Hashtable cofferMenus;

    public CoffeeIterator(Hashtable cofferMenus) {
        this.cofferMenus = cofferMenus;
    }

    @Override
    public boolean hasNext() {
        return cofferMenus.values().iterator().hasNext();
    }

    @Override
    public Object next() {
        return cofferMenus.values().iterator().next();
    }

    @Override
    public void remove() {
        cofferMenus.values().iterator().remove();
    }
}
