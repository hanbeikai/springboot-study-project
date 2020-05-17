package com.beikai.springboottestdemo.Thread.AboutBook.chapter2_1;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/17
 * Time: 10:57 上午
 * Description: No Description
 */
public class SynchronizedOfThread9_04 {

    public static void main(String[] args) {


        Service service = new Service();
        ThreadDemo901 threadDemo901 = new ThreadDemo901(service);
        threadDemo901.setName("a");
        threadDemo901.start();


        ThreadDemo902 threadDemo902 = new ThreadDemo902(service);
        threadDemo902.setName("b");
        threadDemo902.start();

        /**{
         * 线程名为1 : a
         * 线程名为2 : a
         * 线程名为3 : b
         * 线程名为4 : b
         *
         *  可以看出 使用synchronize(服务类.class) 修饰的也是同步执行的
         */
    }


}

class ThreadDemo901 extends Thread{

    private Service service;

    public ThreadDemo901(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        try {
            service.testDemo01();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadDemo902 extends Thread{

    private Service service;

    public ThreadDemo902(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.testDemo02();
    }
}

class Service{
    public static void testDemo01() throws InterruptedException {
        synchronized (Servce.class){
            System.out.println("线程名为1 : " + Thread.currentThread().getName());
            Thread.sleep(1000);
            System.out.println("线程名为2 : " + Thread.currentThread().getName());
        }
    }

    public static void testDemo02(){
        synchronized (Servce.class){
            System.out.println("线程名为3 : " + Thread.currentThread().getName());
            System.out.println("线程名为4 : " + Thread.currentThread().getName());
        }
    }
}
