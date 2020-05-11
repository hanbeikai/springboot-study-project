package com.beikai.springboottestdemo.Thread.AboutBook.chapter2_1;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/8
 * Time: 11:20 下午
 * Description: 解决异步循环
 *
 *   Volatile关键字的作用是  :  使变量在多个线程间可见
 *
 *    如果不是在多继承的情况下,使用继承thread和使用实现runnable的方式创建的线程没有区别
 *
 */
public class VolatileOfThreadDemo03 {

    public static void main(String[] args) throws InterruptedException {
        // 测试未加Volatile关键字时的效果
        PrintString3 printString = new PrintString3();
        printString.start();

        Thread.sleep(1000);
        System.out.println("我要停止线程 : " + Thread.currentThread().getName());
        printString.setContainPrint(false);
        System.out.println("已经被赋值了");

        /**{
         *
         * 未使用关键字时 :
         *  打印的结果是  :
         *  调用线程 : Thread-0
         *  我要停止线程 : main
         *  调用线程 : main 停止循环
         *  已经被赋值了
         *
         *  但是如果虚拟机设置为服务器模式 -server,则会发生死循环, "  调用线程结束 : " 一直未被执行!,这是因为:
         *  在启动线程的时候,变量 isContainPrint 存在公有堆栈和私有堆栈中, 而服务器模式情况下,为了追求效率,线程内部只方位线程内部的私有变量,而 printString.setContainPrint(false); 方法
         *  改变的是 公有变量的值,对于线程内部不而言,改变后的值是不可见的,所以一直处于死循环的情况
         *
         *
         *  使用关键字 volatile
         *  打印的结果是 :
         *  调用线程开始 : Thread-0
         *  我要停止线程 : main
         *  调用线程 : main 停止循环
         *  已经被赋值了
         *  调用线程结束 : Thread-0
         *
         *  可以看出 "调用线程结束 : " 被执行了
         *  这是因为 isContainPrint 被volatile 修饰,线程每次读取这个变量是从公共堆栈中读取的,是最新的,而不是自己私有堆栈中的变量
         *
         *  注意 ,
         *      volatile 有个确定,不支持原子性,只是增加了线程之间变量的可见性
         *
         */
    }


}

class PrintString3 extends Thread{
    // 未使用关键字 volatile
    // private Boolean isContainPrint = true;
    volatile private Boolean isContainPrint = true;
    public Boolean getContainPrint() {
        return isContainPrint;
    }

    public void setContainPrint(Boolean containPrint) {
        System.out.println("调用线程 : " + Thread.currentThread().getName() + " 停止循环");
        isContainPrint = containPrint;
    }

    @Override
    public void run() {
        System.out.println("调用线程开始 : " + Thread.currentThread().getName());
        while (isContainPrint){
        }
        System.out.println("调用线程结束 : " + Thread.currentThread().getName());
    }
}
