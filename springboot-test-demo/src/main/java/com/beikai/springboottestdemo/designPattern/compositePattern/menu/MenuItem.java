package com.beikai.springboottestdemo.designPattern.compositePattern.menu;

import com.beikai.springboottestdemo.designPattern.compositePattern.Iterator.NullIterator;
import com.beikai.springboottestdemo.designPattern.compositePattern.basic.MenuComponent;
import lombok.Data;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/7
 * Time: 10:18 上午
 * Description: No Description
 */
@Data
public class MenuItem extends MenuComponent {
    // 菜名
    private String name;
    // 描述
    private String description;
    // 是否是素菜
    private Boolean isVegetarian;
    // 价格
    private Double price;

    public MenuItem(String name, String description, Boolean isVegetarian, Double price) {
        this.name = name;
        this.description = description;
        this.isVegetarian = isVegetarian;
        this.price = price;
    }

    @Override
    public void print() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                        ", description='" + description + '\'' +
                        ", isVegetarian=" + isVegetarian +
                        ", price=" + price;
    }

    @Override
    public Iterator createIterator() {
        return new NullIterator();
    }
}
