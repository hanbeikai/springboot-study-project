package com.beikai.springboottestdemo.singletest;

/**
 * @ClassName SingleDemo04
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
 *  SingleDemo04 懒汉式 (线程安全,采用同步锁)
 *
 *  解决了 SingleDemo03 中线程安全的问题,但是效率低,因为对象的实力话只需要执行一次就可以了,其他时候通过return 返回,但是由于同步锁加在了方法上,每一次调用这个方法
 *  都通过了锁,效率低
 **/
public class SingleDemo04{
    private static SingleDemo04 singleDemo04;

    private SingleDemo04(){}

    public static synchronized SingleDemo04 getInstance(){
        if (null == singleDemo04){
            singleDemo04 = new SingleDemo04();
        }

        return singleDemo04;
    }
}
