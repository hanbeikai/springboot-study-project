package com.beikai.springboottestdemo.Thread.AboutBook.chapter1_1;

/**
 * @ClassName Test05
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/13 19:07
 * @Version 1.0
 **/
public class Test06 {
    public static void main(String[] args) {
        MyThread006 myThread005 = new MyThread006();
        Thread thread = new Thread(myThread005,"a");
        Thread thread2 = new Thread(myThread005,"b");
        Thread thread3 = new Thread(myThread005,"c");
        Thread thread4 = new Thread(myThread005,"e");
        Thread thread5 = new Thread(myThread005,"f");

        thread.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }

}

class MyThread006 extends Thread{
    private int i = 5;
    @Override
    public void run() {
        super.run();
        while (i > 0){
            i--;
            System.out.println("当前值是 : " + Thread.currentThread().getName() +"===" + this.i);
        }
    }
}
