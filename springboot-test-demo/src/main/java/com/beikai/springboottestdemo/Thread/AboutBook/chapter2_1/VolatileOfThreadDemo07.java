package com.beikai.springboottestdemo.Thread.AboutBook.chapter2_1;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/9
 * Time: 7:38 下午
 * Description: synchronize 代码块有 Volatile 同步功能
 *
 *  synchronize 可以使多个线程访问同一个资源具有同步性,也可以是线程内的私有堆栈中的变量与公共堆栈中的变量同步
 *
 */
public class VolatileOfThreadDemo07 {

    public static void main(String[] args) throws InterruptedException {
        Servce servce = new Servce();

        StartThread startThread = new StartThread(servce);
        startThread.start();

        Thread.sleep(1000);

        StopThread stopThread = new StopThread(servce);
        stopThread.start();

        System.out.println("已经发起停止循环的命令了!");

        /**{
         *  打印的结果 :
         * 已经发起停止循环的命令了!
         *
         *
         * 从上可以知道,startThread 中的循环被执行了,并且在已经用另一个线程来设置循环中断时没有效果的,这是因为线程间的变量是没有互通的
         *
         * 解决的办法有两种
         *  1 使用 Volatile 关键字
         *  2 使用 同步关键字修饰代码,使其具有可视性
         *
         *
         *      方法2
         *             // 使用synchronize 解决 线程变量的可视性
         *             synchronized (anything){
         *
         *             }
         *   之后打印的结果是 :
         *      已经发起停止循环的命令了!
         *      停下来了!
         *
         *    可以看出,synchronize 使线程间的变量具有可视性,
         *
         *     synchronize 关键字的作用是保证同一时刻,只有一个线程可以执行 关键字修饰的代码块或方法,这就包含了两个特性: 互斥性 与 可见性,
         *     同步关键字不仅可以解决一个线程看到的对象不同的问题,还保证进入同步代码块或方法的线程,看到的对象是同一个锁的上一个线程修改后的对象
         *
         *
         *
         */
    }
}

class Servce{

    private boolean isStop = true;

    public void startMethod(){

        String anything = new String();
        while (isStop){

            // 使用synchronize 解决 线程变量的可视性
            synchronized (anything){

            }
        }
        System.out.println("停下来了!");
    }

    public void stopMethod(){
        isStop = false;
    }
}

class StartThread extends Thread{
    private Servce servce;

    public StartThread(Servce servce) {
        this.servce = servce;
    }

    @Override
    public void run() {
        servce.startMethod();
    }
}


class StopThread extends Thread{
    private Servce servce;

    public StopThread(Servce servce) {
        this.servce = servce;
    }

    @Override
    public void run() {
        servce.stopMethod();
    }
}
