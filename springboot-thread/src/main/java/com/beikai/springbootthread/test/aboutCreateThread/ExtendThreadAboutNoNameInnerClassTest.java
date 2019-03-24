package com.beikai.springbootthread.test.aboutCreateThread;

/**
 * @ClassName ExtendThreadAboutNoNameInnerClassTest
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/3 16:31
 * @Version 1.0
 *  通过匿名内部类创建线程
 **/
public class ExtendThreadAboutNoNameInnerClassTest extends Thread{

    public static void main(String[] args) {
        // 创建无参线程对象
        new Thread(){
            @Override
            public void run() {
                System.out.println("创建无参线程对象成功...");
            }
        }.start();

        // 创建带有线程任务的线程对象
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("创建带有线程任务的线程对象...");
            }
        }).start();

        // 创建带有线程任务并且重写run方法的线程对象
        // 这种方法运行的是 "override run 线程执行了" , 这是因为thread 实现了runable接口, 而runnable接口中有个run方法
        // 所以,最终调用的是 thread中的run方法,而不是runnable中的run方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable run 线程执行了...");
            }
        }){
            @Override
            public void run() {
                System.out.println("override run 线程执行了");
            }
        }.start();
    }
}
