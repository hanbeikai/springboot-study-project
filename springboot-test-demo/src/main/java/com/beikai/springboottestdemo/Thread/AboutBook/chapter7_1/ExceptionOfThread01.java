package com.beikai.springboottestdemo.Thread.AboutBook.chapter7_1;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/3
 * Time: 8:40 下午
 * Description: 线程中异常的处理
 *
 *      线程中的异常一般难以捕捉,有两种方式可以解决
 *          方式一 :
 *               对指定线程对象进行异常的捕捉
 *
 *         MyThread05 myThread05 = new MyThread05();
 *         // 方式一 指定线程对象设置 自定义 异常捕捉
 *         myThread05.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
 *             @Override
 *             public void uncaughtException(Thread t, Throwable e) {
 *                 System.out.println("线程 " + t.getName() + " 出现了异常");
 *                 e.printStackTrace();
 *             }
 *         });
 *
 *          方式二 :
 *               对指定线程类下的所有线程对象设置默认异常捕捉
 *
 */
public class ExceptionOfThread01 {

    public static void main(String[] args) {
        MyThread05 myThread05 = new MyThread05();
        // 方式一 指定线程对象设置 自定义 异常捕捉
        myThread05.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("线程 " + t.getName() + " 出现了异常");
                e.printStackTrace();
            }
        });
        myThread05.start();

        // 未设置 异常处理
        MyThread05 myThread051 = new MyThread05();
        myThread051.start();


        /**
         * 线程 Thread-0 出现了异常
         * Exception in thread "Thread-1" java.lang.NullPointerException
         * 	at com.beikai.springboottestdemo.Thread.AboutBook.chapter7_1.MyThread05.run(ExceptionOfThread01.java:49)
         *
         *
         * java.lang.NullPointerException
         * 	at com.beikai.springboottestdemo.Thread.AboutBook.chapter7_1.MyThread05.run(ExceptionOfThread01.java:49)
         *
         *  从上面的打印可以看出,异常被捕捉到了
         *
         */
    }

}

class MyThread05 extends Thread{

    @Override
    public void run() {
        Object o = null;
        System.out.println("object 的 hashCode 为 : " + o.hashCode());
    }
}
