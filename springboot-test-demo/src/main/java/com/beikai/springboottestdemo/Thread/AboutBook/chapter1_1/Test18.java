package com.beikai.springboottestdemo.Thread.AboutBook.chapter1_1;

/**
 * @ClassName Test18
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/14 11:42
 * @Version 1.0
 **/
public class Test18 {
    public static void main(String[] args) {
        try {
            MyObject myObject = new MyObject();
            Thread thread = new Thread(() -> {
                myObject.setValue("22", "22");
            });
            thread.setName("a");
            thread.start();
            Thread.sleep(1000);

            Thread thread1 = new Thread(()->{
                myObject.printUsernamePassword();
            });
            thread1.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyObject {
    private String username = "1";
    private String password = "11";

    public void setValue(String username, String password) {
        this.username = username;
        if (Thread.currentThread().getName().equals("a")) {
            System.out.println("停止a线程 .. ");
            Thread.currentThread().suspend();
        }
        this.password = password;
    }

    public void printUsernamePassword() {
        System.out.println(username + "---" + password);
    }
}
