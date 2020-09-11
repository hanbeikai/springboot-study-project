package com.beikai.springboottestdemo.designPattern.proxyPattern.dynamicProxyPattern.basic;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/11
 * Time: 7:55 上午
 * Description: 基本接口
 */
public interface PersonBean {

    String getName();
    String getGender();
    String getInterests();
    Integer getHotOrNotRating();

    void setName(String name);
    void setGender(String gender);
    void setInterests(String interests);
    void setHotOrNotRating(Integer rating);
}
