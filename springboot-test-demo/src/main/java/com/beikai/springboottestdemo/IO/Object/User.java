package com.beikai.springboottestdemo.IO.Object;

import java.io.Serializable;

/**
 * @ClassName User
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/3 21:20
 * @Version 1.0
 *  实现Serializable  生成序列主键
 **/
public class User implements Serializable {

    private static final long serialVersionUID = 5644866295561055296L;

    private String username;
    private String password;
    private String age;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
