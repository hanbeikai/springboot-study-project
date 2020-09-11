package com.beikai.springboottestdemo.designPattern.proxyPattern.dynamicProxyPattern.impl;

import com.beikai.springboottestdemo.designPattern.proxyPattern.dynamicProxyPattern.basic.PersonBean;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/11
 * Time: 7:59 上午
 * Description: No Description
 */
public class PersonBeanImpl implements PersonBean {

    String name;
    String gender;
    // 兴趣
    String interests;
    // 评分
    int rating;
    // 评分人数
    int ratingCount = 0;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getGender() {
        return this.gender;
    }

    @Override
    public String getInterests() {
        return this.interests;
    }

    @Override
    public Integer getHotOrNotRating() {
        if (ratingCount == 0) {
            return 0;
        }
        return (rating / ratingCount);
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public void setInterests(String interests) {
        this.interests = interests;
    }

    @Override
    public void setHotOrNotRating(Integer rating) {
        // 评分相加
        this.rating += rating;
        // 评分人数加1
        ratingCount++;
    }

    public PersonBeanImpl(String name, String gender, String interests) {
        this.name = name;
        this.gender = gender;
        this.interests = interests;
    }
}
