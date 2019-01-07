package com.beikai.springbootthread.service.impl;

import java.util.concurrent.Callable;

/**
 * @ClassName StudyFutureTask
 * @Description TODO
 * @Author Admin
 * @Date 2019/1/7 21:05
 * @Version 1.0
 * futuretask 学习 类
 * <p>
 * /**
 * * futureTaks 特征 特点
 * *  1 . 泛型定义
 * *  2. 构造函数 要有一个callable
 * *  3. futuretask 应该实现了runnable 实现了run方法
 * *  4. 有一个get方法可以返回callable的执行结果 此方法还具有阻塞的效果
 *
 *     把一个大的知识点, 拆分成多个步骤,逐个突破,
 *
 *      在使用推理的方式中,第一次不可能全部摸透,总的有个概括,然后再看其中不懂的部分,一步步的拓展知识面
 *
 *     拓展 :
 *      线程池的关闭:
 *          一种是 : 立即关闭(不管是否有线程立即终止)
 *              是否能够立刻终止?
 *                  如果能停止,为什么能停止? ---> 涉及到线程终止的问题
 *                      线程终止中有哪些api是安全的关闭线程? ---> 也不是所有的状态都能够终止,一些规定的状态可以终止,其他的 ...
 *                          线程状态又有哪些那? ---> 通过查看源码 可以知道 线程的状态又 6中 [new,runable,blocked,waiting,timed-waiting,terminated]
 *          另一种是 : 等里面的线程全部执行完后关闭
 *
 */

public class StudyFutureTask<T> implements Runnable {

    /**
     * // 封装 callable对象
     */
    Callable<T> callable;

    /**
     * 创建一个泛型的结果对象  保存callable的执行结果
     * @param callable
     */
    T result;

    /**
     * 创建一个线程状态  start -> end
     * @param callable
     */
    String state = "start";

    public StudyFutureTask(Callable callable) {
        this.callable=callable;
    }

    @Override
    public void run() {
        // 线程执行的方法
        try {
          result = callable.call();
        } catch (Exception e) { // 可以拓展  state = "exception"
            state = "exception";
            e.printStackTrace();
        }finally {
            state = "end";
        }

        // 当线程执行完毕后 通知等待的线程开始执行
        synchronized (this){
            System.out.println("执行结束,通知正在等待结果的线程");
            this.notify();
        }
    }

    public T get() throws InterruptedException {
        // 其他线程是否已经结束
        if ("end".equals(state)){
            return result;
        }

        // 如果还有线程没有结束 获取当前正在执行的线程名
        System.out.println(Thread.currentThread().getName() + "   线程还没有执行完毕,应该再等一会");

        // 采用锁的形式 让主线程等待  每一个对象都有 一个wait 和 notify的 方法
        synchronized (this){
            this.wait();
        }
        return result;
    }
}
