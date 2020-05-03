package com.beikai.springboottestdemo.Thread.AboutBook.chapter7_1;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/3
 * Time: 9:51 下午
 * Description: 测试 线程异常处理的传递
 */
public class ExceptionOfThread03 {

    public static void main(String[] args) {

        // 对线程的相关测试
        //testThread();

        // 对线程组的相关测试
        testThreadGroup();


    }

    private static void testThreadGroup() {
        MyThreadGroup04 myThreadGroup04 = new MyThreadGroup04("测试线程组");
        MyThread09 myThread09 = new MyThread09(myThreadGroup04, "测试线程");

        // 设置类 异常捕捉
        MyThread09.setDefaultUncaughtExceptionHandler(new StateUnCaughtException());

        // 设置对象异常捕捉
        //myThread09.setUncaughtExceptionHandler(new ObjectUnCaughtException());

        myThread09.start();

        /**  自定义线程池组的情况下,
         *      *  类异常捕捉和对象异常捕捉同时使用,会用到 对象异常捕捉
         *                  *  对对象进行异常处理
         *                  * java.lang.NumberFormatException: For input string: "a"
         *                  * 	at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
         *                  * 	at java.lang.Integer.parseInt(Integer.java:580)
         *                  * 	at java.lang.Integer.parseInt(Integer.java:615)
         *                  * 	at com.beikai.springboottestdemo.Thread.AboutBook.chapter7_1.MyThread09.run(ExceptionOfThread03.java:40)
         *          *
         *        只使用类异常捕捉
         *                  对类进场异常处理!
         * 调用线程组中的 uncaughtException 方法
         * java.lang.NumberFormatException: For input string: "a"
         * 	at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
         * 	at java.lang.Integer.parseInt(Integer.java:580)
         * 	at java.lang.Integer.parseInt(Integer.java:615)
         * 	at com.beikai.springboottestdemo.Thread.AboutBook.chapter7_1.MyThread09.run(ExceptionOfThread03.java:97)
         * java.lang.NumberFormatException: For input string: "a"
         * 	at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
         * 	at java.lang.Integer.parseInt(Integer.java:580)
         * 	at java.lang.Integer.parseInt(Integer.java:615)
         * 	at com.beikai.springboottestdemo.Thread.AboutBook.chapter7_1.MyThread09.run(ExceptionOfThread03.java:97)
         *
         *
         * 	    不用 对象异常捕捉和类异常捕捉
         *Exception in thread "测试线程" java.lang.NumberFormatException: For input string: "a"
         * 	at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
         * 	at java.lang.Integer.parseInt(Integer.java:580)
         * 	at java.lang.Integer.parseInt(Integer.java:615)
         * 	at com.beikai.springboottestdemo.Thread.AboutBook.chapter7_1.MyThread09.run(ExceptionOfThread03.java:107)
         * java.lang.NumberFormatException: For input string: "a"
         * 	at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
         * 	at java.lang.Integer.parseInt(Integer.java:580)
         * 	at java.lang.Integer.parseInt(Integer.java:615)
         * 	at com.beikai.springboottestdemo.Thread.AboutBook.chapter7_1.MyThread09.run(ExceptionOfThread03.java:107)
         * 调用线程组中的 uncaughtException 方法
         *
         *      如果对自定义中的 重写方法 中去掉 super.uncaughtException(t, e);  异常将不会被  类异常捕捉 获取到(此时 使用了类异常捕捉器)
         *
         *调用线程组中的 uncaughtException 方法
         * java.lang.NumberFormatException: For input string: "a"
         * 	at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
         * 	at java.lang.Integer.parseInt(Integer.java:580)
         * 	at java.lang.Integer.parseInt(Integer.java:615)
         * 	at com.beikai.springboottestdemo.Thread.AboutBook.chapter7_1.MyThread09.run(ExceptionOfThread03.java:117)
         *
         */
    }

    private static void testThread() {
        // 类异常捕获
        Thread.setDefaultUncaughtExceptionHandler(new StateUnCaughtException());
        MyThread09 myThread09 = new MyThread09();
        // 对象异常捕获
        //myThread09.setUncaughtExceptionHandler(new ObjectUnCaughtException());
        myThread09.start();


        /**
         *  类异常捕捉和对象异常捕捉同时使用,会用到 对象异常捕捉
         *  对对象进行异常处理
         * java.lang.NumberFormatException: For input string: "a"
         * 	at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
         * 	at java.lang.Integer.parseInt(Integer.java:580)
         * 	at java.lang.Integer.parseInt(Integer.java:615)
         * 	at com.beikai.springboottestdemo.Thread.AboutBook.chapter7_1.MyThread09.run(ExceptionOfThread03.java:40)
         *
         * 	只使用对类异常捕捉
         *对类进场异常处理!
         * java.lang.NumberFormatException: For input string: "a"
         * 	at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
         * 	at java.lang.Integer.parseInt(Integer.java:580)
         * 	at java.lang.Integer.parseInt(Integer.java:615)
         * 	at com.beikai.springboottestdemo.Thread.AboutBook.chapter7_1.MyThread09.run(ExceptionOfThread03.java:52)
         */}
}


class MyThread09 extends Thread {
    private String num = "a";

    public MyThread09() {
        super();
    }

    public MyThread09(ThreadGroup group, String name) {
        super(group, name);
    }

    @Override
    public void run() {
        int i = Integer.parseInt(num);
        System.out.println("在线程中打印 : " + (i + 1));
    }
}

class MyThreadGroup04 extends ThreadGroup {

    public MyThreadGroup04(String name) {
        super(name);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        //super.uncaughtException(t, e);
        System.out.println("调用线程组中的 uncaughtException 方法");
        e.printStackTrace();
    }
}

class ObjectUnCaughtException implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("对对象进行异常处理");
        e.printStackTrace();
    }
}

class StateUnCaughtException implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {

        System.out.println("对类进场异常处理!");
        e.printStackTrace();
    }
}