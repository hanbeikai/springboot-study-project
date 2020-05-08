package com.beikai.springboottestdemo.Thread.AboutBook.chapter2_1;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/8
 * Time: 11:20 下午
 * Description: 解决同步循环
 *
 *   Volatile关键字的作用是  :  使变量在多个线程间可见
 *
 *    如果不是在多继承的情况下,使用继承thread和使用实现runnable的方式创建的线程没有区别
 *
 */
public class VolatileOfThreadDemo02 {

    public static void main(String[] args) throws InterruptedException {
        // 测试未加Volatile关键字时的效果
        PrintString2 printString = new PrintString2();
        new Thread(printString).start();
        System.out.println("我要停止线程 : " + Thread.currentThread().getName());
        Thread.sleep(2000);
        printString.setContainPrint(false);

        /**{
         *  打印的结果是  :
         * 我要停止线程 : main
         * 调用线程 : Thread-0
         * thread name : Thread-0
         * thread name : Thread-0
         * thread name : Thread-0
         * 调用线程结束 : Thread-0
         *
         *  可以看出来,通过多线程可以实现 停止死循环
         *
         *  注意 :
         *      如果这个代码运行在-server 服务器模式中64bit的jvm上,会出现死循环,
         *
         *      解决的办法是 :
         *          使用Volatile关键字
         *          volatile 关键字从的作用是强制使线程从公共堆栈中读取数据,而不是从线程的私有堆栈中读取数据
         *
         *
         *
         */
    }


}

class PrintString2 implements Runnable{

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

    @Override
    public void run() {
        System.out.println("调用线程 : " + Thread.currentThread().getName());
        PrintStringMethod();
        System.out.println("调用线程结束 : " + Thread.currentThread().getName());
    }
}
