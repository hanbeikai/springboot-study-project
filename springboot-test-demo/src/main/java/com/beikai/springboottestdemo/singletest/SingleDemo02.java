package com.beikai.springboottestdemo.singletest;

/**
 * @ClassName SingleDemo02
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/9 18:36
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
 *      SingleDemo02 使用的是 饿汉式(静态代码块) 可用
 *
 *      优点 : 在类装载的时候就创建了对象,优点同SingleDemo01 相同, 使用了静态代码块 , 在类装载的时候执行了静态代码块中的代码
 *      缺点 : 在类装载的时候就已经加载到内存中了,如果没有被引用, 则会造成内存的浪费
 **/
public class SingleDemo02{
    private static SingleDemo02 singleDemo02;

    static {
        singleDemo02 = new SingleDemo02();
    }

    private SingleDemo02(){}

    public static SingleDemo02 getInstance(){

        return singleDemo02;
    }
}
