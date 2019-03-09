package com.beikai.springboottestdemo.singletest;

/**
 * @ClassName SingleDemo01
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/9 18:11
 * @Version 1.0
 *   单例模式的使用场景:
 *      有些时候系统只需要一个全局对象,从配置文件中获取配置信息,然后其他对对象通过这个单例对象获取配置信息,简化了复杂环境下的配置管理
 *
 *  创建单例的方法:
 *      1. 构造私有化,不提供new 这个对象的方法
 *      2. 提供一个静态方法,用于获取这个单例对象,在这个方法中,如果对应引用为null,则创建一个对象,如果对象引用不为空,则返回对应的引用
 *
 *
 *   注意:
 *      单例模式在多线程下的应用必须十分小心,防止多线程情况下创建两个以上的实例对象,解决的办法是添加锁,虽然效率降低了,但是安全
 *
 *    SingleDemo01 采用的是 饿汉式(静态常量) 可以使用
 *
 *    优点 : 这种写法比较简单,在类装载的时候就完成了实例化,避免了线程同步问题
 *    缺点 : 在类装载的时候实现了实例化,没有达到懒加载的效果,如果这个对象从头到尾没有使用过,则会造成内存的浪费
 *
 **/
public class SingleDemo01 {
    private static final SingleDemo01 SINGLE_DEMO_01 = new SingleDemo01();

    private SingleDemo01(){}

    public static SingleDemo01 getInstance(){
        return SINGLE_DEMO_01;
    }
}


