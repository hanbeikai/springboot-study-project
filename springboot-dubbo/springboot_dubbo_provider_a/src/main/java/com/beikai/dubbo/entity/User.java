package com.beikai.dubbo.entity;

import java.io.Serializable;

/**
 * Created by Beikai.Han on 2019/3/4.
 *  序列化model
 */
public class User implements Serializable{

    private static final long serialVersionUID = 2931386888007798659L;

    // 主键
    private long id;
    // 邮箱
    private String email;
    // 昵称
    private String nickName;
    // 密码
    private String password;
    //
    private String regTime;
    // 账户
    private String userName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
