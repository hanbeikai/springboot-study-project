package com.beikai.springboottestdemo.Thread.AboutBook.chapter2_1;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/8
 * Time: 11:20 下午
 * Description: Volatile关键字与死循环
 *
 *   Volatile关键字的作用是  :  使变量在多个线程间可见
 *
 *    如果不是在多继承的情况下,使用继承thread和使用实现runnable的方式创建的线程没有区别
 *
 */
public class VolatileOfThreadDemo01 {

    public static void main(String[] args) {
        // 测试未加Volatile关键字时的效果
        PrintString printString = new PrintString();
        printString.PrintStringMethod();

        System.out.println("我要停止线程 : " + Thread.currentThread().getName());

        printString.setContainPrint(false);

        /**{
         *  打印的结果是  :
         *  thread name : main
         * thread name : main
         * thread name : main
         * thread name : main
         * thread name : main
         * thread name : main
         * thread name : main
         * thread name : main
         * thread name : main
         * thread name : main
         * thread name : main
         * thread name : main
         * thread name : main
         * thread name : main
         * ...
         *
         * 线程一直在 死循环中执行, 后面的 "我要停止下线程 并没有被打印出来,因为主线程在死循环中没有出来,后面设置 setContainPrint 根本没有执行到"
         *
         * 解决思路是 有另一个不受主线程控制的线程执行 setContainPrint 来告诉主线程 循环要停止了
         *
         */
    }


}

class PrintString{

    private Boolean isContainPrint = true;

    public Boolean getContainPrint() {
        return isContainPrint;
    }

    public void setContainPrint(Boolean containPrint) {
        isContainPrint = containPrint;
    }

    void PrintStringMethod(){
        while (isContainPrint){
            System.out.println("thread name : " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
