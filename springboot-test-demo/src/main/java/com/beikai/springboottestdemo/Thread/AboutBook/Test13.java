package com.beikai.springboottestdemo.Thread.AboutBook;

/**
 * @ClassName Test13
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/14 10:42
 * @Version 1.0
 **/
public class Test13 {
    public static void main(String[] args) {
        try {
            TestUser testUser = new TestUser();
            MyThread13 myThread13 = new MyThread13(testUser);
            myThread13.start();
            Thread.sleep(50);
            // 线程终止
            myThread13.stop();
            System.out.println(testUser.getUsername() + "----" + testUser.getPassword());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread13 extends Thread {
    private TestUser testUser;

    public MyThread13(TestUser testUser) {
        super();
        this.testUser = testUser;
    }

    @Override
    public void run() {
        super.run();
        testUser.printUser("cc", "bb");
    }
}

class TestUser {
    private String username = "a";
    private String password = "b";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public synchronized void printUser(String username, String password) {
        try {
            this.username = username;
            Thread.sleep(1000);
            this.password = password;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}