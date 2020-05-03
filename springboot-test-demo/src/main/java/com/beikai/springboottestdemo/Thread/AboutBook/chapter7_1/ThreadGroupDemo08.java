package com.beikai.springboottestdemo.Thread.AboutBook.chapter7_1;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/3
 * Time: 6:03 下午
 * Description: 递归与非递归获取组内对象
 *
 */
public class ThreadGroupDemo08 {

    public static void main(String[] args) {
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();

        // 显示加入线程组
        ThreadGroup threadGroupA = new ThreadGroup(threadGroup,"A");
        Runnable runnable = () -> {
            System.out.println(" run thread ... ");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        // 显示加入线程组
        ThreadGroup threadGroupB = new ThreadGroup(threadGroupA,"B");

        ThreadGroup[] threadGroupsA = new ThreadGroup[Thread.currentThread().getThreadGroup().activeGroupCount()];
        // 使用true 表示获取子组以及子孙组等
        Thread.currentThread().getThreadGroup().enumerate(threadGroupsA,true);
        for (int i = 0; i < threadGroupsA.length; i++) {
            ThreadGroup group = threadGroupsA[i];
            if (null != group) {
                System.out.println("线程组a 中的 线程组名 分别为 : " + group.getName());
            }
        }


        ThreadGroup[] threadGroupsB = new ThreadGroup[Thread.currentThread().getThreadGroup().activeGroupCount()];
        // 非递归获取组内线程
        Thread.currentThread().getThreadGroup().enumerate(threadGroupsB,true);
        // 递归获取组内线程
        for (int i = 0; i < threadGroupsB.length; i++) {
            ThreadGroup group = threadGroupsB[i];
            if (null != group) {
                System.out.println("线程组b 中的 线程组名 分别为 : " + group.getName());
            }
        }

    }
}
