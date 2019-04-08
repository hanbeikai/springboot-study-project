package com.beikai.springboottestdemo.Thread;

import com.beikai.springboottestdemo.Thread.test.BankAccount;
import com.beikai.springboottestdemo.Thread.test.BankAccount1;
import com.beikai.springboottestdemo.Thread.test.BankAccount2;
import com.beikai.springboottestdemo.Thread.test.PersionThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

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
 * <p>
 * <p>
 * 关于死锁:
 * 当多个线程同步时,他们获取锁的顺序不一致,导致线程相互等待的情况,称之为死锁
 * <p>
 * 避免死锁:
 * 保持锁的顺序都一致
 * <p>
 * <p>
 * 生产者消费者设计模式
 * 设计模式是别人总结的一套问题的解决方案,这个解决方案被大多数人熟知和认可
 * <p>
 * 生产者消费者设计模式解决了数据的平衡问题
 * <p>
 * 创建线程的方式有三种:
 * 1. 实现runable
 * 可以在不影响当前类的实现和继承的情况下开启线程
 * 2. 继承thread
 * 3. 使用callable
 **/

/**
 *  继承 thread, 重写run()方法, run()方法中的代码块是要执行的代码块
 */
class MyThread2 extends Thread {

    @Override
    public void run() {
        // 在子线程打印 100 行字符串
        for (int i = 1; i < 101; i++) {
            System.out.println("子线程打印 : " + i);
        }
    }
}

/**
 * 实现 runable的方式实现线程
 */
class MyThread3 implements Runnable{

    /**
     * 调用线程的方法
     */
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("实现runable的线程 : " + i);
        }
    }
}

/**
 * 实现 callable的方式创建线程
 */
class MyThread4 implements Callable<Integer>{

    /**
     * 重写 call 的方法  在方法中填写代码块
     * @return
     * @throws Exception
     */
    @Override
    public Integer call() throws Exception {
        int num = 0;
        for (int i = 0; i < 100; i++) {
            num += i;
        }
        System.out.println("执行完子线程后 ,返回结果 : " + num);
        return num;
    }
}

public class Test01 {

    public static void main(String[] args) {

        switchMethod(11);

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
            case 7:
                // 通过继承Thread的方式创建线程
                createThreadType01();
                break;

            case 8:
                // 通过实现runable的方式创建线程
                createThreadType02();
                break;

            case  9:
                // 通过 callable的方式创建线程
                createThreadType03();
                break;
            case 10:
                // 线程的一些常用方法
                aboutThreadMethod();
                break;
            case 11:
                // 关于线程的生命周期
                aboutThreadLife();
                break;
            case 12:
                // 关于线程的优先级
                aboutThreadOrder();
                break;
            default:
                break;
        }
    }

    /**
     * 关于线程优先级的测试
     */
    private static void aboutThreadOrder() {

    }

    /**
     * 关于线程的生命周期
     *
     *   thread.getState()  获取线程的状态
     *      1. NEW        Thread state for a thread which has not yet started. 线程还没有执行start()方法时的状态
     *      2. RUNNABLE   Thread state for a runnable thread    线程已经运行,也就是 执行过start() 后的状态
     *      3. BLOCKED    Thread state for a thread blocked waiting for a monitor lock  线程被阻塞,正在等待监视器锁
     *      4. WAITING    Thread state for a waiting thread.   线程正在等待
     *      5. TIMED_WAITING   Thread state for a waiting thread with a specified waiting time  线程正在等待,以一个特点的时间
     *      6. TERMINATED   hread state for a terminated thread. 线程回一个已终止状态
     *
     *   getState() 返回的 state是一个枚举类类型,java 中的数据类型是 : 类,接口,数组,枚举类型
     *
     *    枚举类型的数据只能取值定义的值, 如state 枚举类型中 的取值只能是上面6个
     *
     *    经常使用枚举类型定义 不连续的值, 如: 星期
     */
    private static void aboutThreadLife() {
        // 创建线程
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        // 查看此时线程的状态
        System.out.println("启动前状态是 : " + thread.getState());
        // 启动线程
        thread.start();
        // 查看启动后的状态
        System.out.println("启动后状态是 : " + thread.getState());

        // 主线程
        for (int i = 0; i < 100; i++) {
            System.out.println("主线程中的值 是 : " + i);
        }

        System.out.println("cpu被抢夺后状态是 : " + thread.getState());

    }

    /**
     * 线程的一些常用方法
     *  Thread.currentThread()  获取当前线程
     *  Thread.currentThread().getName()  获取当前线程名称
     *  thread.setName("线程1")  设置线程名的方式1
     *  new Thread(new runable(){},"线程2"); 通过构造的方式设置线程名
     *  thread2.setDaemon(true);  设置线程为守护线程, 守护线程是服务线程的,当jvm中只有守护线程是,jvm会退出
     *  thread.isAlive()  判断线程是否还活着
     */
    private static void aboutThreadMethod() {
        // 创建一个线程
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + "--->" + i);
                }
            }
        });
        thread.setName("线程1");
        // 启动线程
        thread.start();

        // 方式2 设置线程名
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 309; i++) {
                    System.out.println(Thread.currentThread().getName() + "--->" + i);
                }
            }
        },"线程2");
        // 设置线程为守护线程
        thread2.setDaemon(true);
        // 启动线程
        thread2.start();

        /**
         * 主线程
         */
        for (int i = 0; i < 100; i++) {
            System.out.println("主线程中打印的数字是 : " + i);
        }

        // 判断线程是否还活着
        System.out.println(thread.isAlive());
        System.out.println(thread2.isAlive());

    }

    /**
     * 通过 callable的方式 创建线程
     *  这种方式可以有返回值, 但是一般情况下不这么使用, 而是在线程池中使用
     *   通过使用线程池的方式,减少创建线程和释放线程的开销
     */
    private static void createThreadType03() {
        // 定义一个类实现 callable
        MyThread4 myThread4 = new MyThread4();
        // 创建一个futuretask对象
        FutureTask<Integer> futureTask = new FutureTask<>(myThread4);
        // 创建一个线程,执行任务对象
        Thread thread = new Thread(futureTask);
        // 执行线程
        thread.start();

        try {
            // 获取线程的结果
            System.out.println("当前线程结束后返回的结果是 : " + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    /**
     * 通过实现 runable的方式创建线程,但是有两种方式
     *
     *      1. 使用匿名内部类
     *      2. 使用实现类
     *
     *      如果从结果来说,两者方式并没有什么区别,但是如果从过程来说, 使用匿名内部类的方式要比使用实现类的方式麻烦, 比如说如果想要开启多个线程, 使用匿名内部类的话,每次都
     *      需要实现 new thread(new runable(){代码块}) ,而使用实现类的方式,每次只需要 thread t = new thread(实现类); t.start();就可以了,
     *
     *      所以,如果创建的线程不多,推荐使用匿名内部类,如果线程多,推荐使用 实现类的方式
     */
    private static void createThreadType02() {

        // 使用匿名内部类的方式
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("匿名内部类的线程 --> " + i);
                }
            }
        }).start();

        // 使用实现类的方式创建线程
        MyThread3 myThread3 = new MyThread3();
        // 创建线程
        Thread thread = new Thread(myThread3);
        // 开启线程
        thread.start();

        // 主线程线程
        for (int j = 0; j < 100; j++) {
            System.out.println("主线程的线程 : " + j);
        }
    }

    /**
     * 通过继承的方式开启线程
     *  当前程序的主线程和子线程同时执行,但是每次运行的结果不同,这是因为多线程中的多个线程,只有抢到cpu的执行权后,才户执行,
     *
     *  在单核cpu中,某一时刻cpu只能执行一个任务,因为cpu的执行速度非常快,可以快速的在各个线程之间进行切换,对于用户而言,感觉就是多个线程同时执行,
     *  对于多线程 cpu中, 同时执行多个线程,所以,多线程推荐使用多核的cpu
     */
    private static void createThreadType01() {
        // 声明继承了 thread 的对象
        MyThread2 myThread2 = new MyThread2();
        //开启线程  如果此处执行run方法,将并不会开启线程,而是执行的一个普通方法而已
        myThread2.start();

        // 在主线程中打印
        for (int i = 0; i < 100; i++) {
            System.out.println("主线程 : " + i);
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

class MyThread extends Thread {

    @Override
    public void run() {
        if ("a".equals(Thread.currentThread().getName())) {
            synchronized ("资源1") {
                System.out.println("线程a获取资源1,也想获取资源2");
                synchronized ("资源2") {
                    System.out.println("线程a同时获取资源1和资源2");
                }
            }
        }
        if ("b".equals(Thread.currentThread().getName())) {
            synchronized ("资源2") {
                System.out.println("线程b获取资源2,也想获取资源1");
                synchronized ("资源1") {
                    System.out.println("线程b同时获取资源1和资源2");
                }
            }
        }
    }
}