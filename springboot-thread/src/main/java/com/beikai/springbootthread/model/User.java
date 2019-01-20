package com.beikai.springbootthread.model;

/**
 * @ClassName UserDao
 * @Description TODO
 * @Author Admin
 * @Date 2019/1/6 23:07
 * @Version 1.0
 *   测试线程的实体类
 **/
public class User {

    private int id;
    private String name;
    private String age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
