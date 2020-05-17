package com.beikai.springboottestdemo.Thread.AboutBook.chapter2_1;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/17
 * Time: 12:05 下午
 * Description: 多线程的死锁问题
 */
public class SynchronizedOfThread12_01 {

    public static void main(String[] args) throws InterruptedException {
        deadLockDemo deadLockDemo = new deadLockDemo();
        deadLockDemo.setName("a");

        Thread thread = new Thread(deadLockDemo);
        thread.start();

        Thread.sleep(100);

        deadLockDemo.setName("b");
        Thread thread1 = new Thread(deadLockDemo);
        thread1.start();

        /**
         *  多线程死锁主要出现的原因是多个线程之间相互等待锁释放
         *
         *  查询死锁的方式
         *
         *  到java 的bin 目录下,执行jsp命令[]
         *
         *  ➜  bin jps
         *     5088 SynchronizedOfThread12_01
         *     5092 Jps
         *     1454
         *     4655 KotlinCompileDaemon
         *     5087 Launcher
         *
         *
         *  可以看到对应的死锁的对象的id值,使用jstack -l id 命令可以看到下列死锁信息
         *
         *
         *  Found one Java-level deadlock:
         * =============================
         * "Thread-1":
         *   waiting to lock monitor 0x00007fc728812558 (object 0x000000076ac9c768, a java.lang.Object),
         *   which is held by "Thread-0"
         * "Thread-0":
         *   waiting to lock monitor 0x00007fc7288110b8 (object 0x000000076ac9c778, a java.lang.Object),
         *   which is held by "Thread-1"
         *
         * Java stack information for the threads listed above:
         * ===================================================
         * "Thread-1":
         * 	at com.beikai.springboottestdemo.Thread.AboutBook.chapter2_1.deadLockDemo.run(SynchronizedOfThread12_01.java:66)
         * 	- waiting to lock <0x000000076ac9c768> (a java.lang.Object)
         * 	- locked <0x000000076ac9c778> (a java.lang.Object)
         * 	at java.lang.Thread.run(Thread.java:748)
         * "Thread-0":
         * 	at com.beikai.springboottestdemo.Thread.AboutBook.chapter2_1.deadLockDemo.run(SynchronizedOfThread12_01.java:51)
         * 	- waiting to lock <0x000000076ac9c778> (a java.lang.Object)
         * 	- locked <0x000000076ac9c768> (a java.lang.Object)
         * 	at java.lang.Thread.run(Thread.java:748)
         *
         * Found 1 deadlock.
         *
         * 所以在设计程序时要避免双方持有多方锁情况
         *
         */

    }
}

class deadLockDemo implements Runnable{

    private String name;
    private Object o1 = new Object();
    private Object o2 = new Object();

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void run() {

        if (name.equals("a")) {
            synchronized (o1){
                System.out.println("name is " + name);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (o2){
                    System.out.println("按照 o1 -> o2 的顺序");
                }
            }
        }

        if (name.equals("b")) {
            synchronized (o2){
                System.out.println("name is " + name);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (o1){
                    System.out.println("按照 o2 -> o1 的顺序");
                }
            }
        }

    }
}
