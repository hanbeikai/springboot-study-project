package com.beikai.springboottestdemo.designPattern.iteratePattern.basic;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/5
 * Time: 5:59 下午
 * Description: 基础菜单类
 */
public interface Menu {
    // 创建 迭代了
    public Iterator createIterator();
}
