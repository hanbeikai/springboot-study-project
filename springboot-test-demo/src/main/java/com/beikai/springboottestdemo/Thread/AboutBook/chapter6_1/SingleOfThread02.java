package com.beikai.springboottestdemo.Thread.AboutBook.chapter6_1;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/2
 * Time: 5:44 下午
 * Description: 单例模式 与 多线程的关系
 * <p>
 * 延迟加载 / 懒汉模式
 * 当需要的时候才调用 new 对象
 * <p>
 * 即调用get方法的时候，先判断是否已经创建了，如果没有，创建，返回
 * 如果已经存在了，直接返回
 * <p>
 * 缺点 ：
 * 和恶汉模式一样，在多线程的情况下会安全，出现多个实例
 */
public class SingleOfThread02 {

    public static void main(String[] args) {
//        MyThread2 myThread = new MyThread2();
//        myThread.start();
//        MyThread2 myThread2 = new MyThread2();
//        myThread2.start();
//        MyThread2 myThread3 = new MyThread2();
//        myThread3.start();

        Connection connection = SingleDemo11.getConnection();

        Thread thread = new Thread(()->{
            System.out.println(connection.hashCode());
        });

        thread.start();

        Thread thread2 = new Thread(()->{
            System.out.println(connection.hashCode());
        });

        thread2.start();

        Thread thread3 = new Thread(()->{
            System.out.println(connection.hashCode());
        });

        thread3.start();

        /**{
         * 获取的值 都相同，说明获取到的是同一个对象
         * 值为 ： 413036867
         * 值为 ： 413036867
         * 值为 ： 413036867
         */
    }
}

class MyThread2 extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println("值为 ： " + SingleDemo2.getSingleDemo().hashCode());
    }
}

// 单例模式2 懒汉模式    安全问题
class SingleDemo2 {
    private static SingleDemo singleDemo;

    public SingleDemo2() {

    }

    public static SingleDemo getSingleDemo() {
        if (null == singleDemo) {
            singleDemo = new SingleDemo();
        }
        return singleDemo;
    }
}

// 单例模式3 使用同步关键字   效率低
class SingleDemo3 {
    private static SingleDemo3 singleDemo3;

    public SingleDemo3() {
    }

    synchronized public static SingleDemo3 getInstance() {
        if (null == singleDemo3) {
            singleDemo3 = new SingleDemo3();
        }
        return singleDemo3;
    }
}

// 单例模式4 同步代码块    效率低
class SingleDemo4 {
    private static SingleDemo4 singleDemo4;

    public SingleDemo4() {
    }

    public static SingleDemo4 getInstance() {
        synchronized (SingleDemo4.class) {
            if (null == singleDemo4) {
                singleDemo4 = new SingleDemo4();
            }

            return singleDemo4;
        }
    }
}

// 单例模式5，关键位置加同步代码块  比方法上的同步关键字效率高
class SingleDemo5 {
    private static SingleDemo5 singleDemo5;

    public SingleDemo5() {
    }

    public static SingleDemo5 getInstance() {
        if (null == singleDemo5) {
            synchronized (SingleDemo5.class) {
                singleDemo5 = new SingleDemo5();
            }
        }

        return singleDemo5;
    }
}

// 单例模式6 使用dcl双否定模式  推荐使用， 大多数多线程解决单例模式安全问题
class SingleDemo6 {
    private volatile static SingleDemo6 singleDemo6;

    public SingleDemo6() {
    }

    public static SingleDemo6 getInstance() {
        if (null == singleDemo6) {
            synchronized (SingleDemo6.class) {
                if (null == singleDemo6) {
                    singleDemo6 = new SingleDemo6();
                }
            }
        }
        return singleDemo6;
    }
}


// 单例模式7 ， 使用静态内置类实现单例模式   内置静态类可以达到线程安全问题，但是遇到序列化对象，仍然会出现多例
class SingleDemo7 {
    private static class MySingleDemo {
        private static SingleDemo7 singleDemo7 = new SingleDemo7();
    }

    public SingleDemo7() {
    }

    public static SingleDemo7 getInstance() {
        return MySingleDemo.singleDemo7;
    }
}

// 单利模式8  序列化和反序列化实现单利模式
class SingleDemo8 implements Serializable {

    // 序列化对象
    private static final long serialVersionUID = -6232959950204252704L;

    // 内部类
    private static class MySingleDemo {
        private static SingleDemo8 singleDemo7 = new SingleDemo8();
    }

    public SingleDemo8() {
    }

    public static SingleDemo8 getInstance() {
        return MySingleDemo.singleDemo7;
    }

    // 如果是使用序列化和反序列化方式，需要调用下面这个方法，如果不调用，会导致序列化和反序列化不同的对象
    protected Object readResolve() {
        System.out.println("调用了 readResolve 方法");
        return MySingleDemo.singleDemo7;
    }
}

// 单例模式9 静态代码块 实现单利模式
class SingleDemo9 {

    private static SingleDemo9 SINGLE_DEMO_9 = null;

    public SingleDemo9() {
    }

    static {
        SINGLE_DEMO_9 = new SingleDemo9();
    }

    public static SingleDemo9 getInstance() {
        return SINGLE_DEMO_9;
    }
}

// 单例模式10 使用枚举的方式实现单例模式   缺点 暴漏了 枚举类
enum SingleDemo10 {
    connectionFactory;
    private Connection connection;
    private SingleDemo10() {
        try {
            System.out.println("调用了SingleDemo10构造！");
            String username = "root";
            String password = "root";
            String url = "jdbc:mysql://192.168.2.224:3306/mytest2?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
            String driverName = "com.mysql.cj.jdbc.Driver";
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection getConnection(){
        return connection;
    }
}

// 单例模式11 避免暴漏 枚举类
class SingleDemo11{
   public enum SingleDemo12 {
        connectionFactory;
        private Connection connection;
        private SingleDemo12() {
            try {
                System.out.println("调用了SingleDemo10构造！");
                String username = "root";
                String password = "root";
                String url = "jdbc:mysql://192.168.2.224:3306/mytest2?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
                String driverName = "com.mysql.cj.jdbc.Driver";
                Class.forName(driverName);
                connection = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        public Connection getConnection(){
            return connection;
        }
    }

    public static Connection getConnection(){
       return SingleDemo12.connectionFactory.connection;
    }
}




