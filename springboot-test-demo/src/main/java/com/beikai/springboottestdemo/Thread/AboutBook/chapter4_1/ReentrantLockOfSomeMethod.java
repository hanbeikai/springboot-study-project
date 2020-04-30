package com.beikai.springboottestdemo.Thread.AboutBook.chapter4_1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/4/29
 * Time: 10:46 下午
 * Description: lock 的一些方法
 * <p>
 * lock.getHoldCount() : 获取当前线程保存此锁的线程个数
 * lock.getQueueLength() ： 返回正等待获取此锁定的线程个数
 * lock.getWaitQueueLength(lock.newCondition()); : 返回等待与此锁定相关的给定条件的condition的线程的估计值
 */
public class ReentrantLockOfSomeMethod {


    public static void main(String[] args) throws InterruptedException {
        // 测试 getHoldCount 方法
        //testGetHoldCount();

        // 测试 getQueueLength 方法
        testGetQueueLength();

        // 测试 getWaitQueueLength 方法
        //testGetWaitQueueLength();

    }

    private static void testGetWaitQueueLength() {

        /**
         * 功能描述:
         start await ...
         start await ...
         start await ...
         start await ...
         start await ...
         start await ...
         start await ...
         start await ...
         start await ...
         start await ...
         start notify ... and have 10 个线程等待新的 condition
         end notify ... and have 9 个线程等待新的 condition
         */
        LockService3 lockService2 = new LockService3();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                lockService2.waitMethod();
            }
        };

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(runnable);

        }

        for (int i = 0; i < threads.length; i++) {
            Thread thread = threads[i];
            thread.start();
        }

        try {
            Thread.sleep(2000);

            lockService2.notifyMethod();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private static void testGetQueueLength() {

        LockService2 lockService2 = new LockService2();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                lockService2.serviceMethod();
            }
        };

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(runnable);

        }

        for (int i = 0; i < threads.length; i++) {
            Thread thread = threads[i];
            thread.start();
        }

        try {
            Thread.sleep(2000);

            System.out.println("当前待定获取锁的线程数为 ： " + lockService2.lock.getQueueLength());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void testGetHoldCount() {
        LockService lockService = new LockService();
        lockService.serviceMethod1();


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                lockService.serviceMethod3();
            }
        });

        thread.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                lockService.serviceMethod4();
            }
        });

        thread2.start();

        System.out.println("当方法3 等待锁的过程中 ，锁的个数为 （如果大于0，说明等待的过程中是释放锁的 ！）" + lockService.lock.getHoldCount());
        System.out.println("等待锁的 线程数为 " + lockService.lock.getQueueLength());
    }
}

class LockService3 {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    void waitMethod() {
        lock.lock();
        try {
            System.out.println("start await ... ");
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void notifyMethod() {
        lock.lock();
        try {
            System.out.println("start notify ... and have " + lock.getWaitQueueLength(condition) + " 个线程等待新的 condition");
            condition.signal();
            System.out.println("end notify ... and have " + lock.getWaitQueueLength(condition) + " 个线程等待新的 condition");
        } finally {
            lock.unlock();
        }
    }
}


class LockService2 {
    public ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    void serviceMethod() {
        lock.lock();
        try {
            System.out.println("thread name is  " + Thread.currentThread().getName() + " 进入线程 ！");
            //Thread.sleep(Integer.MAX_VALUE);
            // thread.sleep 会占有锁，但是condition.await 会释放锁
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class LockService {

    public ReentrantLock lock = new ReentrantLock();

    void serviceMethod4() {
        lock.lock();
        try {
            System.out.println("serviceMethod4 get lock");
            System.out.println("等待锁的 线程数为 " + lock.getQueueLength());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void serviceMethod3() {
        lock.lock();
        try {
            Condition condition = lock.newCondition();
            System.out.println("等待前 " + lock.getHoldCount());
            condition.await();
            //Thread.sleep(5000);
            System.out.println("等待后 " + lock.getHoldCount());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void serviceMethod1() {
        lock.lock();
        try {
            System.out.println("serviceMethod1 get count " + lock.getHoldCount());
            serviceMethod2();
        } finally {
            lock.unlock();
        }

    }

    void serviceMethod2() {
        lock.lock();
        try {
            System.out.println("serviceMethod1 get count " + lock.getHoldCount());
            //serviceMethod3();
        } finally {
            lock.unlock();
        }

    }
}