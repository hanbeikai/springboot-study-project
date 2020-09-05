package com.beikai.springboottestdemo.designPattern.orderPattern.model;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/8/20
 * Time: 1:30 下午
 * Description: 风扇模型
 */
public class Ceiling {

    public static final Integer hight = 3;
    public static final Integer mid = 2;
    public static final Integer low = 1;
    public static final Integer stop=0;
    private String location;
    private Integer speed;

    public Ceiling(String location) {
        this.location = location;
        this.speed = stop;
    }

    // 设置高速
    public void setHight(){
        speed = hight;
        System.out.println(location + " -- 风扇设置为高转速");
    }

    public void setMid(){
        speed = mid;
        System.out.println(location + " -- 风扇设置为中转速");
    }

    public void setLow(){
        speed = low;
        System.out.println(location + " -- 风扇设置为低转速");
    }

    public void off(){
        speed = stop;
        System.out.println(location + " -- 风扇设置为关闭");
    }

    public int getSpeed(){
        return speed;
    }
}
