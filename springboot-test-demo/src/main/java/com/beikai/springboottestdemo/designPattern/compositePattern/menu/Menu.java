package com.beikai.springboottestdemo.designPattern.compositePattern.menu;

import com.beikai.springboottestdemo.designPattern.compositePattern.Iterator.CompositeIterator;
import com.beikai.springboottestdemo.designPattern.compositePattern.basic.MenuComponent;
import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/7
 * Time: 10:17 上午
 * Description: No Description
 */
@Data
public class Menu extends MenuComponent {
    private List<MenuComponent> menuComponents = new ArrayList<>();
    private String name;
    private String description;

    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public void add(MenuComponent menuComponent) {
        menuComponents.add(menuComponent);
    }

    @Override
    public void remove(MenuComponent menuComponent) {
        menuComponent.remove(menuComponent);
    }

    @Override
    public MenuComponent getChild(int i) {
        return menuComponents.get(i);
    }

    @Override
    public void print() {

        // 如果菜单下还有菜单, 调用每个菜单的打印方法,打印内部参数
        Iterator<MenuComponent> iterator = menuComponents.iterator();
        while (iterator.hasNext()) {
            iterator.next().print();
        }

    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                        ", description='" + description + '\'';
    }

    @Override
    public Iterator<MenuComponent> createIterator() {
        return new CompositeIterator(menuComponents.iterator());
    }
}
