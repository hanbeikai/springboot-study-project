package com.beikai.springboottestdemo.singletest;

/**
 * @ClassName SingleDemo05
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
 *  SingleDemo5 懒汉式 (线程安全, 同步代码块)
 *
 *      这种方式解决了SingleDemo4中 锁加载方法上造成的效率低问题,但是这种方式实际并不能保证线程安全问题,会遇到与singledemo3中的问题,
 *      当一个线程执行了 if (null == singleDemo05) 的时候, 另一个线程也执行到了这个,会产生多例
 *
 **/
public class SingleDemo05{
    private static SingleDemo05 singleDemo05;

    private SingleDemo05(){}

    public static SingleDemo05 getInstance(){
        if (null == singleDemo05){
            synchronized (SingleDemo05.class){
                singleDemo05 = new SingleDemo05();
            }
        }
        return singleDemo05;
    }
}
