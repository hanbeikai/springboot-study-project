package com.beikai.springboottestdemo.Thread.AboutBook.chapter1_1;

/**
 * @ClassName Test05
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/13 19:07
 * @Version 1.0
 **/
public class Test05 {
    public static void main(String[] args) {
        MyThread005 myThread005 = new MyThread005("a");
        MyThread005 myThread006 = new MyThread005("b");
        MyThread005 myThread007 = new MyThread005("c");

        myThread005.start();
        myThread006.start();
        myThread007.start();
    }

}
class MyThread005 extends Thread{
    private int i = 5;
    public MyThread005(String name){
        super();
        this.setName(name);
    }
    @Override
    public void run() {
        super.run();
        while (i > 0){
            i--;
            System.out.println("当前值是 : " + Thread.currentThread().getName() +"===" + this.i);
        }
    }
}
