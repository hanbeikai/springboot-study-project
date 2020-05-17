package com.beikai.springboottestdemo.Thread.AboutBook.chapter2_1;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/17
 * Time: 11:30 上午
 * Description: 同步Synchronized 方法无限等待和解决
 */
public class SynchronizedOfThread11_02 {

    public static void main(String[] args) {
        Service11 service11 = new Service11();
        Thread1101 thread1101 = new Thread1101(service11);
        thread1101.setName("a");
        thread1101.start();

        Thread1102 thread1102 = new Thread1102(service11);
        thread1102.setName("a1");
        thread1102.start();


        Thread1102 thread11022 = new Thread1102(service11);
        thread11022.setName("a2");
        thread11022.start();

        Thread1102 thread110222 = new Thread1102(service11);
        thread110222.setName("a3");
        thread110222.start();

        /**{
         * method a start ... a
         * method b start ... a1
         * method b end ... a1
         * method b start ... a3
         * method b end ... a3
         * method b start ... a2
         * method b end ... a2
         *
         *
         * 从上可以看出,使用Synchronized(object) 同步代码块,可以解决使用同步方法死循环问题
         * 对于不同的方法,执行顺序是异步,对于多个线程调用同一个方法,执行的顺序是同步
         */

    }


}

class Thread1101 extends Thread{

    private Service11 service11;

    public Thread1101(Service11 service11) {
        this.service11 = service11;
    }

    @Override
    public void run() {
        service11.testDemo01();
    }
}
class Thread1102 extends Thread{

    private Service11 service11;

    public Thread1102(Service11 service11) {
        this.service11 = service11;
    }

    @Override
    public void run() {
        service11.testDemo02();
    }
}

class Service11 {
    Object o1 = new Object();
    void testDemo01(){
        synchronized (o1){
            System.out.println("method a start ... " + Thread.currentThread().getName());
            while (true){

            }
            //System.out.println("method a end ... " + Thread.currentThread().getName());
        }
    }

    Object o2 = new Object();
    void testDemo02(){
        synchronized (o2){
            System.out.println("method b start ... " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("method b end ... " + Thread.currentThread().getName());
        }
    }
}
