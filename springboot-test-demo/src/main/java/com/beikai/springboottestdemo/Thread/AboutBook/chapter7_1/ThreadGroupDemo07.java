package com.beikai.springboottestdemo.Thread.AboutBook.chapter7_1;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/3
 * Time: 4:56 下午
 * Description: 线程组内线程批量停止
 *      使用 线程组 调用 interrupt() 会逐步关掉组内的线程
 */
public class ThreadGroupDemo07 {

    public static void main(String[] args) throws InterruptedException {

        ThreadGroup threadGroup = new ThreadGroup("新的线程组");
        for (int i = 0; i < 5; i++) {
            MyThread4 thread = new MyThread4(threadGroup,"thread-" + (1 + i));
            thread.start();
        }

        Thread.sleep(1000);
        threadGroup.interrupt();
        System.out.println("调用了线程终止的方法!");

        /**
         * 线程 thread-1 开始陷入死循环了!....
         * 线程 thread-2 开始陷入死循环了!....
         * 线程 thread-3 开始陷入死循环了!....
         * 线程 thread-4 开始陷入死循环了!....
         * 线程 thread-5 开始陷入死循环了!....
         * 调用了线程终止的方法!
         * 线程 thread-4 线程已经停止了
         * 线程 thread-3 线程已经停止了
         * 线程 thread-5 线程已经停止了
         * 线程 thread-2 线程已经停止了
         * 线程 thread-1 线程已经停止了
         */
    }
}

class MyThread4 extends Thread{

    public MyThread4(ThreadGroup threadGroup,String name) {
        super(threadGroup,name);
    }

    @Override
    public void run() {
        System.out.println("线程 "+Thread.currentThread().getName()+ " 开始陷入死循环了!....");

        while (!this.isInterrupted()){

        }
        System.out.println("线程 "+Thread.currentThread().getName()+ " 线程已经停止了");
    }
}
