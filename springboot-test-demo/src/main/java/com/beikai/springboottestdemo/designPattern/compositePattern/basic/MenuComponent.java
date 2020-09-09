package com.beikai.springboottestdemo.designPattern.compositePattern.basic;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/7
 * Time: 10:01 上午
 * Description: 组合模式基本
 */
public abstract class MenuComponent {

    // -------------------------组合方法---------------------
    // 新增
    public void add(MenuComponent menuComponent){
        throw new UnsupportedOperationException();
    }
    // 删除
    public void remove(MenuComponent menuComponent){
        throw new UnsupportedOperationException();
    }
    // 获取子
    public MenuComponent getChild(int i){
        throw new UnsupportedOperationException();
    }


    // ------------------------操作方法-------------------------
    public String getName(){
        throw new UnsupportedOperationException();
    }
    public String getDescription(){
        throw new UnsupportedOperationException();
    }
    public Double getPrice(){
        throw new UnsupportedOperationException();
    }
    public Boolean getIsVegetarian(){
        throw new UnsupportedOperationException();
    }
    public void print(){
        throw new UnsupportedOperationException();
    }

    // 添加迭代器
    public Iterator createIterator(){
        throw new UnsupportedOperationException();
    }

}
