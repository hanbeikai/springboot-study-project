package com.beikai.springboottestdemo.Thread.AboutBook;

/**
 * @ClassName Test02
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/13 18:33
 * @Version 1.0
 **/
public class Test03 {
    public static void main(String[] args) {
        MyThread002 myThread002 = new MyThread002(1);
        MyThread002 myThread003 = new MyThread002(2);
        MyThread002 myThread004 = new MyThread002(3);
        MyThread002 myThread005 = new MyThread002(4);
        MyThread002 myThread006 = new MyThread002(5);
        MyThread002 myThread007 = new MyThread002(6);
        MyThread002 myThread008 = new MyThread002(7);
        MyThread002 myThread009 = new MyThread002(8);

        myThread002.start();
        myThread003.start();
        myThread004.start();
        myThread005.start();
        myThread006.start();
        myThread007.start();
        myThread008.start();
        myThread009.start();

    }
}

class MyThread002 extends Thread{

    private int i = 0;

    public MyThread002(int i){
        this.i = i;
    }
    
    @Override
    public void run() {
        System.out.println("创建线程 : " + this.i);
    }
}
