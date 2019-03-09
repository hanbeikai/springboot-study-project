package com.beikai.springboottestdemo.singletest;

/**
 * @ClassName SingleDemo09
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
 *      通过jdk 1.5中添加枚举类型实现单例模式,不仅避免多线程同步问题,而且还能防止反序列化重新创建对象
 *
 *      优点 : 系统中只存在一个对象,节省了系统资源,对于一些需要频繁创建销毁的对象,使用单例模式可以提高性能
 *      缺点 : 当想要实例话的时候,必须记住使用响应的获取对象的方式,而不是使用new ,可能会造成其他开发人员的困扰,特别是看不到源码的时候
 *
 *      适用的场合:
 *          需要频繁的进行创建和销毁的对象；
 *          创建对象时耗时过多或耗费资源过多，但又经常用到的对象；
 *          工具类对象；
 *          频繁访问数据库或文件的对象。
 **/
public enum SingleDemo09{

    SINGLE_DEMO_09;

    private String name;
    private String age;

    public String getInstance(){

        System.out.println("调用了这个方法");

        return this.getClass().toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
