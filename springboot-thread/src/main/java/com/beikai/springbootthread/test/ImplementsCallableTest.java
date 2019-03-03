package com.beikai.springbootthread.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName ImplementsCallableTest
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/3 16:53
 * @Version 1.0
 *
 * 创建带有返回值的线程
 *
 **/
public class ImplementsCallableTest implements Callable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建线程对象
        ImplementsCallableTest implementsCallableTest = new ImplementsCallableTest();
        // 创建任务调用线程对象
        FutureTask<Integer> futureTask = new FutureTask<>(implementsCallableTest);
        // 创建线程 添加任务
        Thread thread = new Thread(futureTask);

        thread.start();

        System.out.println("执行别的业务逻辑 ... 因为futuretask是提前完成任务的...");
        // 执行线程, 返回结果
        Integer integer = futureTask.get();

        System.out.println("线程中的运算结果是 : "+ integer);

    }

    @Override
    public Object call() throws Exception {

        int result = 1;
        System.out.println("业务逻辑计算中 ... ");
        Thread.sleep(1000);

        return result;
    }
}
