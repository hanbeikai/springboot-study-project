package com.beikai.springboottestdemo.singletest;

/**
 * @ClassName SingleDemo03
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/9 18:36
 * @Version 1.0
 *
 * 单例模式的使用场景:
 *  *      有些时候系统只需要一个全局对象,从配置文件中获取配置信息,然后其他对对象通过这个单例对象获取配置信息,简化了复杂环境下的配置管理
 *  *
 *  *  创建单例的方法:
 *  *      1. 构造私有化,不提供new 这个对象的方法
 *  *      2. 提供一个静态方法,用于获取这个单例对象,在这个方法中,如果对应引用为null,则创建一个对象,如果对象引用不为空,则返回对应的引用
 *  *
 *  *
 *  *   注意:
 *  *      单例模式在多线程下的应用必须十分小心,防止多线程情况下创建两个以上的实例对象,解决的办法是添加锁,虽然效率降低了,但是安全
 *  *
 *
 *      SingleDemo03 使用了懒汉式 (线程不安全)
 *
 *      这种方式使用了懒加载的方式,但是只能在单线程下使用,如果使用多线程,当执行到 if (null == singleDemo03) 后,另一个线程也执行到了这
 *      则会产生多个实例,所以多线程不可使用
 **/
public class SingleDemo03{
    private static SingleDemo03 singleDemo03;

    private SingleDemo03(){}

    public static SingleDemo03 getInstance(){
        if (null == singleDemo03){
            singleDemo03 = new SingleDemo03();
        }
        return singleDemo03;
    }
}
