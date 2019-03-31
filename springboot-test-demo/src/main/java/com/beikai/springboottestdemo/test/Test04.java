package com.beikai.springboottestdemo.test;

/**
 * @ClassName Test04
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
        return "Test04{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", username='" + username + '\'' +
                '}';
    }


}
