package com.beikai.service;

/**
 * @ClassName CommonService
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/21 7:43
 * @Version 1.0
 *  通用的服务
 **/
public class CommonService {

    private String name;
    private int time;

    public void doService(){
        System.out.println(name + " " + time);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
