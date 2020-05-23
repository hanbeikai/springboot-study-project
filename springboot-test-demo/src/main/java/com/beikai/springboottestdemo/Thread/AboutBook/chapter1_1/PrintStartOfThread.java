package com.beikai.springboottestdemo.Thread.AboutBook.chapter1_1;

import lombok.SneakyThrows;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/5/23
 * Time: 7:44 下午
 * Description: 使用wait 和 notify 交替打印
 */
public class PrintStartOfThread {

    public static void main(String[] args) {
        BdTools bdTools = new BdTools();

        for (int i = 0; i < 20; i++) {
            PaThread paThread = new PaThread(bdTools);
            paThread.start();

            PbThread pbThread = new PbThread(bdTools);
            pbThread.start();
        }
    }
}

class PaThread extends Thread{
    private BdTools bdTools;

    public PaThread(BdTools bdTools) {
        this.bdTools = bdTools;
    }

    @SneakyThrows
    @Override
    public void run() {
        bdTools.printA();
    }
}

class PbThread extends Thread{
    private BdTools bdTools;

    public PbThread(BdTools bdTools) {
        this.bdTools = bdTools;
    }

    @SneakyThrows
    @Override
    public void run() {
        bdTools.printB();
    }
}


class BdTools{
    volatile  private static boolean isPrintA = true;

    synchronized public void printA() throws InterruptedException {
        while (!isPrintA){
            this.wait();
        }

        System.out.println("AAAAAAAAAAAAAAAA");
        isPrintA = false;
        this.notifyAll();
    }

    synchronized public void printB() throws InterruptedException {
        while (isPrintA){
            this.wait();
        }
        System.out.println("BBBBBBBBBBBBBBBB");
        isPrintA = true;
        this.notifyAll();
    }
}