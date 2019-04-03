package com.beikai.springboottestdemo.test;

import org.junit.Test;

/**
 * @ClassName Test04AboutPrintStream
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/31 19:39
 * @Version 1.0
 **/
public class Test04 {
    private String name;
    private String age;
    private String username;
    public String getName() {
        return name;
    }
    public Test04 setName(String name) {
        this.name = name;
        return this;
    }
    public String getAge() {
        return age;
    }
    public Test04 setAge(String age) {
        this.age = age;
        return this;
    }
    public String getUsername() {
        return username;
    }
    public Test04 setUsername(String username) {
        this.username = username;
        return this;
    }
    @Override
    public String toString() {
        return "Test04AboutPrintStream{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
    @Test
    public void test02() {
        Test04 test04 = new Test04().setAge("12").setName("张三").setUsername("zhangsan");
        System.out.println(test04);
    }
}
