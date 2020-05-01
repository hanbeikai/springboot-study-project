package com.beikai.springboottestdemo.Thread.AboutBook.chapter4_1;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/1
 * Time: 8:33 下午
 * Description:
 *  由于ReentrantLock 具有排他性和互斥性，同一时刻只有获取到锁的线程可以执行下去，
 *  其他线程必须等待这个获取到锁的线程释放锁后，
 *  争夺到锁，才可以执行下去，这就产生一个效率问题，在不涉及到数据操作的地方，不用保持这种排他性的锁
 *
 *  所以，创建了另一个类，用来对不涉及到数据操作的线程加锁
 *      所有有了 读写锁的概念  ReentrantReadWriteLock
 *
 *      这个类中，可以理解为又两把锁
 *          读锁 ： 读锁又叫共享锁，当对数据进行读去的时候，可以对多个线程加锁
 *
 *          写锁 ： 写锁又叫排他锁，当对数据进行写入操作的时候，只能有一个线程获取到锁
 *
 *      读锁与写锁的关系
 *          读锁与读锁 之间不排斥
 *          读锁与写锁 之间排斥
 *          写锁与写锁 之间排斥
 *
 *
 *
 */
public class LockOfReadAndWrite {

    public static void main(String[] args) throws InterruptedException {
        // 测试读锁 读读共享
        //testReadLockDemo();
        // 测试写锁 写写互斥
        // testWriteLockDemo();

        // 写读 互斥测试
        testWriteAndReadLockDemo();

        // 测试 混合 读写互斥
        //testReadAndWateLockDemo();

    }

    private static void testReadAndWateLockDemo() throws InterruptedException {
        ReadAndWriteService readAndWriteService = new ReadAndWriteService();

        Thread thread2 = new Thread(() -> {
            readAndWriteService.read();
        });

        thread2.setName("thread-read2");
        thread2.start();

        Thread.sleep(1000);

        Thread thread = new Thread(() -> {
            readAndWriteService.write();
        });
        thread.setName("thread-write1");
        thread.start();


        /** 先读 再写 互斥
         *  线程 thread-read2  获取到读锁 1588339776259
         *  线程 thread-write1 获取到写锁 1588339786264
         *
         */

    }

    private static void testWriteAndReadLockDemo() throws InterruptedException {
        ReadAndWriteService readAndWriteService = new ReadAndWriteService();


        Thread thread = new Thread(() -> {
            readAndWriteService.write();
        });
        thread.setName("thread-write1");
        thread.start();

        Thread.sleep(1000);
        Thread thread2 = new Thread(() -> {
            readAndWriteService.read();
        });

        thread2.setName("thread-read2");
        thread2.start();


        /** 先读 再写 互斥
         *  线程 thread-write1 获取到写锁 1588339966413
         *  线程 thread-read2  获取到读锁 1588339976414
         *
         */


    }

    private static void testWriteLockDemo() {
        ReadAndWriteService readAndWriteService = new ReadAndWriteService();
        Thread thread = new Thread(() -> {
            readAndWriteService.write();
        });

        Thread thread2 = new Thread(() -> {
            readAndWriteService.write();
        });

        thread.setName("thread-write1");
        thread.start();

        thread2.setName("thread-write2");
        thread2.start();


        /**
         *  写锁 ：
         *      lock.writeLock().lock();
         *
         *  线程 thread-read1 获取到锁 1588339350886
         *  线程 thread-read1 获取到锁 1588339360890
         *
         *  从上可以看出，对于写锁，同一时间只能有一个线程获取到锁
         */


    }

    // 读锁 测试
    private static void testReadLockDemo() {
        ReadAndWriteService readAndWriteService = new ReadAndWriteService();
        Thread thread = new Thread(() -> {
            readAndWriteService.read();
        });

        Thread thread2 = new Thread(() -> {
            readAndWriteService.read();
        });

        thread.setName("thread-read1");
        thread.start();

        thread2.setName("thread-read2");
        thread2.start();


        /**
         *  读锁   lock.readLock().lock();
         *      线程 thread-read1 获取到锁 1588339066382
         *      线程 thread-read1 获取到锁 1588339066382
         *
         *     可以看出，获取读锁的时间是一样的，允许多个同一时间获取到锁，
         *
         *
         *
         *
         *
         */
    }

}

class ReadAndWriteService{
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    void read(){
        lock.readLock().lock();
        try {
            System.out.println("线程 " + Thread.currentThread().getName() + "  获取到读锁 " + System.currentTimeMillis());
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }

    void write(){
        lock.writeLock().lock();
        try {
            System.out.println("线程 " + Thread.currentThread().getName() + " 获取到写锁 " + System.currentTimeMillis());
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }

    }
}
