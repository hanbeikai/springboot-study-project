package com.beikai.springboottestdemo.Thread;

import com.beikai.springboottestdemo.Thread.test.BankAccount;
import com.beikai.springboottestdemo.Thread.test.BankAccount1;
import com.beikai.springboottestdemo.Thread.test.BankAccount2;
import com.beikai.springboottestdemo.Thread.test.PersionThread;

/**
 * @ClassName Test01
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/7 14:47
 * @Version 1.0
 * <p>
 * 关于反射的一些 方法
 * <p>
 * 进程:
 * 操作系统运行的一个程序
 * 线程:
 * 线程的一个执行单元,一条执行路径
 * 如: 电脑管家可以同时执行病毒查杀,电脑加速,软件分析等,每个都是一个线程
 * 一个进程至少有一个线程,一个线程如果有多个线程,这是一个多线程程序
 * 每个线程独享一个栈空间,但是他们共享堆区和方法区
 * 主线程:
 * 运行main方法的线程就是主线程(jvm启动主线程,主线程运行main方法)
 * 用户线程:
 * 开启的新的线程,也叫子线程
 * 守护线程:
 * 守护线程是为其他线程提供服务的,不能独立运行,当jvm中只有守护线程的时候,jvm会退出.
 * 如: 垃圾回收器就是一个守护线程
 * <p>
 * *    线程终止:
 * thread.stop()  // 已过时
 * 一般通过修改线程标志的方式让线程运行结束
 * <p>
 * 线程安全问题:
 * 当多个线程同时操作堆区或方法区某个数据的时候,可能会出现一些数据不一致的情况,称之为线程安全问题
 * <p>
 * 堆区:
 * 对象的实例变量(对象在堆区,实例变量在对象里,所以可以说是对象的实例变量)
 * 方法区:
 * 静态变量
 * <p>
 * 线程安全问题解决方法:
 * 1. 每个线程访问自己的局部变量
 * 2. 如果多个线程必须同时操作实例变量或静态变量,可以采用线程同步技术
 * <p>
 * 线程同步技术解决什么问题?
 * 当一个线程在操作期间,不允许其他线程加入
 * 某一段代码在某一时刻只能由一个线程执行
 * <p>
 * <p>
 * 线程同步的方式:
 * 1. 使用同步代码块:
 * synchronized(锁对象){同步代码块}
 * 工作原理:
 * 1). 任意对象都可以作为锁对象,每个对象都有一个内置锁
 * 2). 某一时刻,锁对象最多只能被一个线程拥有
 * 3). 线程在获取锁对象后,直到执行完同步代码块后才会释放锁
 * 4). 线程要执行同步代码块,必须要先获取锁对象
 * 场景描述:
 * 有 a 和 b 两个线程都想要执行同步代码块
 * 1). 线程a获取cpu执行权,获取锁对象,执行同步代码块
 * 2). 在线程a执行同步代码块期间,线程b获取cpu执行权,线程a转为就绪状态
 * 3). 线程b想要获取锁对象,由于同一时间锁对象只能由一个线程获取,此时已经被a获取,所以线程b进入等待锁对象池,变成阻塞状态
 * 4). 线程a获取cpu执行权,并执行完同步代码块,释放线锁对象,转换为就绪状态
 * 5). 等待锁对象池中的线程b获取锁对象,转为就绪状态,获取cpu执行权,执行同步代码块
 * <p>
 *
 *
 * 关于死锁:
 *  当多个线程同步时,他们获取锁的顺序不一致,导致线程相互等待的情况,称之为死锁
 *
 *  避免死锁:
 *      保持锁的顺序都一致
 *
 *
 *  生产者消费者设计模式
 *      设计模式是别人总结的一套问题的解决方案,这个解决方案被大多数人熟知和认可
 *
 *      生产者消费者设计模式解决了数据的平衡问题
 *
 *
 *
 **/
public class Test01 {

    public static void main(String[] args) {

        switchMethod(6);

    }

    private static void switchMethod(int num) {
        switch (num) {
            case 1:
                // 关于 ReentrantLock 的使用方式
                aboutReentrantLock();
                break;
            case 2:
                // 同步代码块的方式
                aboutSynchronized();
                break;
            case 3:
                // 关于同步代码块的使用
                aboutUseSynchronized();
                break;
            case 4:
                // 关于同步方法的使用
                aboutUseSynchronizedMethod();
                break;
            case 5:
                // 关于静态同步方法的使用
                aboutUseSynchronizedStaticMethod();
                break;
            case 6:
                // 关于死锁演示
                aboutDeadLock();
                break;
            default:
                break;
        }
    }

    /**
     * 关于死锁的演示
     *  关于死锁:
     *  *  当多个线程同步时,他们获取锁的顺序不一致,导致线程相互等待的情况,称之为死锁
     *  *
     *  *  避免死锁:
     *  *      保持锁的顺序都一致
     */
    private static void aboutDeadLock() {

        MyThread myThread = new MyThread();
        myThread.setName("a");
        myThread.start();

        MyThread myThread2 = new MyThread();
        myThread2.setName("b");
        myThread2.start();

    }

    /**
     * 关于静态同步方法的使用
     */
    private static void aboutUseSynchronizedStaticMethod() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Test01.m7();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Test01.m8();
            }
        }).start();
    }

    /**
     * 关于同步方法的使用
     * 使用Synchronized修饰方法,默认的锁对象是 this对象,如果方法是静态方法,则默认的人锁对象是 类的字节码对象 例如test.class
     */
    private static void aboutUseSynchronizedMethod() {

        Test01 test01 = new Test01();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test01.m5();
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                test01.m6();
            }
        }).start();

    }

    /**
     * 关于同步代码块的使用
     */
    private static void aboutUseSynchronized() {
        Test01 test01 = new Test01();

        // 执行m1
        new Thread(new Runnable() {
            @Override
            public void run() {
                test01.m1();
            }
        }).start();
        // 执行m2
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Test01().m2();
                // new Test01().m1();
                // 上述方法不可以同步,因为锁对象不是同一个
            }
        }).start();

        // 执行m3
        new Thread(new Runnable() {
            @Override
            public void run() {
                Test01.m3();

                // new Test01().m1();
                // 上述方法不可以同步,因为锁对象不是同一个
            }
        }).start();
    }

    /**
     * 关于 ReentrantLock 的使用方式
     */
    private static void aboutReentrantLock() {
        // 先开户
        BankAccount bankAccount = new BankAccount1();
        // 创建线程
        PersionThread zhangsan = new PersionThread(bankAccount);
        PersionThread lisi = new PersionThread(bankAccount);
        PersionThread wangwu = new PersionThread(bankAccount);

        zhangsan.setName("张三  ");
        lisi.setName("李四  ");
        wangwu.setName("王五  ");

        // 启动线程
        zhangsan.start();
        lisi.start();
        wangwu.start();
    }

    /**
     * 关于同步代码块的方式
     */
    private static void aboutSynchronized() {
        // 先开户
        BankAccount bankAccount = new BankAccount2();
        // 创建线程
        PersionThread zhangsan = new PersionThread(bankAccount);
        PersionThread lisi = new PersionThread(bankAccount);
        PersionThread wangwu = new PersionThread(bankAccount);

        zhangsan.setName("张三  ");
        lisi.setName("李四  ");
        wangwu.setName("王五  ");

        // 启动线程
        zhangsan.start();
        lisi.start();
        wangwu.start();
    }

    /**
     * 同步代码块中的 synchronized 中一般都是使用的自定义的object 对象
     * 一个以使用this 来代替当前调用这个方法的对象
     * <p>
     * 有的时候 也会以被调用了类的字节码对象作为锁对象,此时就不需要事先声明一个对象了
     */
    public void m1() {
        System.out.println("调用了m1方法");
        synchronized (Test01.class) {
            for (int i = 0; i < 100; i++) {
                System.out.println("m1 执行的结果是 : " + i);
            }
        }
        System.out.println("结束了调用m1方法");
    }

    private static final Object OBJECT = new Object();

    /**
     * 使用 自定义 object 对象作为锁
     */
    public void m2() {
        System.out.println("调用了m2方法");
        synchronized (Test01.class) {
            for (int i = 0; i < 100; i++) {
                System.out.println("m2 执行的结果是 : " + i);
            }
        }
        System.out.println("结束了调用m2方法");
    }

    /**
     * 使用 自定义 当前方法的字节码对象作为锁
     * 如果方法是静态的,则锁对象不可以是this,可以是Test01.class
     */
    public static void m3() {
        System.out.println("调用了m3方法");
        synchronized (Test01.class) {
            for (int i = 0; i < 100; i++) {
                System.out.println("m3 执行的结果是 : " + i);
            }
        }
        System.out.println("结束了调用m3方法");
    }

    /**
     * 使用synchronized修饰方法
     */
    public synchronized void m5() {
        System.out.println("调用了m6方法");
        synchronized (Test01.class) {
            for (int i = 0; i < 100; i++) {
                System.out.println("m6 执行的结果是 : " + i);
            }
        }
        System.out.println("结束了调用m6方法");
    }

    /**
     * 使用synchronized修饰整个方法中的代码块
     */
    public void m6() {
        synchronized (this) {
            System.out.println("调用了m6方法");
            for (int i = 0; i < 100; i++) {
                System.out.println("m6 执行的结果是 : " + i);
            }
            System.out.println("结束了调用m6方法");
        }
    }

    /**
     * 使用synchronized修饰方法
     */
    public static synchronized void m7() {
        System.out.println("调用了m7方法");
        for (int i = 0; i < 100; i++) {
            System.out.println("m7 执行的结果是 : " + i);

        }
        System.out.println("结束了调用m7方法");
    }

    /**
     * 使用synchronized修饰整个方法中的代码块
     */
    public static void m8() {
        synchronized (Test01.class) {
            System.out.println("调用了m8方法");
            for (int i = 0; i < 100; i++) {
                System.out.println("m8 执行的结果是 : " + i);
            }
            System.out.println("结束了调用m8方法");
        }
    }
}

class MyThread extends Thread{

    @Override
    public void run() {
        if ("a".equals(Thread.currentThread().getName())){
            synchronized ("资源1"){
                System.out.println("线程a获取资源1,也想获取资源2");
                synchronized ("资源2"){
                    System.out.println("线程a同时获取资源1和资源2");
                }
            }
        }
        if ("b".equals(Thread.currentThread().getName())){
            synchronized ("资源2"){
                System.out.println("线程b获取资源2,也想获取资源1");
                synchronized ("资源1"){
                    System.out.println("线程b同时获取资源1和资源2");
                }
            }
        }
    }
}