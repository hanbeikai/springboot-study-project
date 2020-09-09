package com.beikai.springboottestdemo.designPattern.compositePattern.Iterator;

import com.beikai.springboottestdemo.designPattern.compositePattern.basic.MenuComponent;
import com.beikai.springboottestdemo.designPattern.compositePattern.menu.Menu;

import java.util.Iterator;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/7
 * Time: 11:02 上午
 * Description: No Description
 */
public class CompositeIterator implements Iterator {

    // 创建一个堆栈 用来存储
    Stack<Iterator<MenuComponent>> stack = new Stack();

    public CompositeIterator(Iterator<MenuComponent> iterator) {
        // 添加到堆栈里
        stack.push(iterator);
    }

    @Override
    public boolean hasNext() {
        // 首先 判断堆栈是否为空 ,如果为空 返回false
        if (stack.isEmpty()) {
            return false;
        }else {
            // 如果堆栈不为空,获取最顶层的迭代器,也就是最后加入的 先进后出
            Iterator<MenuComponent> peek = stack.peek(); // 获取到的是list的迭代器
            // 判断当前迭代器是否还有下一个元素
            if (!peek.hasNext()) { // 调用的是list的迭代器中的 hasnext
                // 如果迭代器没有下一个了,将它弹出堆栈 ,判断堆栈中下一个迭代器是否为空
                stack.pop();
                return hasNext();
            }else {
                // 如果迭代器中有元素
                return true;
            }
        }
    }

    @Override
    public Object next() {
        // 判断当前迭代器是否还有下一个元素
        if (hasNext()) {
            // 获取顶层的迭代器
            Iterator<MenuComponent> peek = stack.peek();
            // 获取迭代器中的元素
            MenuComponent next = peek.next();
            if (next instanceof Menu) {
                // 如果当前项是菜单项,则把菜单项的子项放入到堆栈中
                stack.push(next.createIterator());
            }
            // 返回当前这一项
            return next;
        }
        return null;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
