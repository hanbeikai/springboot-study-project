package com.beikai.springboottestdemo.Thread.AboutBook.chapter7_1;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/3
 * Time: 11:44 上午
 * Description: 线程组 具有自动关机归属特性
 *  创建一个线程组,如果没有设置归属的线程组,会自动添加到当前所在线程组中
 *
 *  enumerate() 的作用是将线程组中的子线程组以复制的形式拷贝到 threadGroup[] 数组对象中
 *  activeGroupCount() 获取当前数组对象中的子线程组数量
 */
public class ThreadGroupDemo03 {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("A处线程 : " + Thread.currentThread().getName()
                + " A处线程所属的线程组是 : " + Thread.currentThread().getThreadGroup().getName()
                + " A处线程所属的线程组中活动数量为 : " + Thread.currentThread().getThreadGroup().activeGroupCount()
        );

        ThreadGroup threadGroup = new ThreadGroup("新的线程组");  // 自动加到main 组中国
        System.out.println("B处线程 : " + Thread.currentThread().getName()
                + " B处线程所属的线程组是 : " + Thread.currentThread().getThreadGroup().getName()
                + " B处线程所属的线程组中活动数量为 : " + Thread.currentThread().getThreadGroup().activeGroupCount()
        );


        ThreadGroup[] threadGroup1 = new ThreadGroup[Thread.currentThread().getThreadGroup().activeGroupCount()+1];
        threadGroup1[1] = new ThreadGroup("新的线程组b");
        Thread.currentThread().getThreadGroup().enumerate(threadGroup1);

        System.out.println("C处线程 : " + Thread.currentThread().getName()
                + " C处线程所属的线程组是 : " + Thread.currentThread().getThreadGroup().getName()
                + " C处线程所属的线程组中活动数量为 : " + Thread.currentThread().getThreadGroup().activeGroupCount()
        );

        for (int i = 0; i < threadGroup1.length; i++) {
            ThreadGroup group = threadGroup1[i];
            if (null != group) {
                System.out.println("线程组名字为 " + group.getName());
            }

        }

    }
}
