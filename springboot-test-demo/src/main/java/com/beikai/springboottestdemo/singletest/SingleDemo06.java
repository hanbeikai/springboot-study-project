package com.beikai.springboottestdemo.singletest;

/**
 * @ClassName SingleDemo06
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/9 18:37
 * @Version 1.0
 *
 * *   单例模式的使用场景:
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
 *  SingleDemo06 双重检查 (推荐使用)
 *
 *  double - check 概念对于多线程开发者不陌生,通过两次 if (null == singleDemo06) 检查,可以保证线程安全,
 *
 *  优点 : 线程安全,延迟加载,效率较高
 *
 **/
public class SingleDemo06{
    private static SingleDemo06 singleDemo06;

    private SingleDemo06(){}

    public static SingleDemo06 getInstance(){
        if (null == singleDemo06){
            synchronized (SingleDemo06.class){
                if (null == singleDemo06) {
                    singleDemo06 = new SingleDemo06();
                }
            }
        }

        return singleDemo06;
    }
}
