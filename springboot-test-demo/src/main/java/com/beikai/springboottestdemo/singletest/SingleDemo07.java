package com.beikai.springboottestdemo.singletest;

/**
 * @ClassName SingleDemo07
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/9 18:38
 * @Version 1.0
 *
 *  *   单例模式的使用场景:
 *  *      有些时候系统只需要一个全局对象,从配置文件中获取配置信息,然后其他对对象通过这个单例对象获取配置信息,简化了复杂环境下的配置管理
 *  *
 *  *  创建单例的方法:
 *  *      1. 构造私有化,不提供new 这个对象的方法
 *  *      2. 提供一个静态方法,用于获取这个单例对象,在这个方法中,如果对应引用为null,则创建一个对象,如果对象引用不为空,则返回对应的引用
 *  *
 *  *
 *  *   注意:
 *  *      单例模式在多线程下的应用必须十分小心,防止多线程情况下创建两个以上的实例对象,解决的办法是添加锁,虽然效率降低了,但是安全
 *
 *  SingleDemo07 静态内部类(推荐使用)
 *
 *  这种方式跟饿汉式采取的机制类似, 但是又不同,两者都采用了类装载来保证初始化的时候只有一个线程,不同的是饿汉式是只要加载singledemo07的时候就会实例化,没有懒加载,
 *  而静态内部类方法在singledemo07的时候不会立即实例化,而是需要调用方法getinstance的时候才会装载singledemo08,从而完成singletemo08的实例化,类的静态属性只会在第一次
 *  加载的时候初始化,所以在这里,jvm帮我们保证了线程的安全性,在类的初始化的时候,别的线程不能进去
 *
 *  优点 : 避免了线程不安全,延时加载,效率高
 **/
public class SingleDemo07{
    private SingleDemo07(){}

    private static class SingleDemo08{
        private static final SingleDemo07 SINGLE_DEMO_08 = new SingleDemo07();
    }

    public static SingleDemo07 getInstance(){
        return SingleDemo08.SINGLE_DEMO_08;
    }
}
