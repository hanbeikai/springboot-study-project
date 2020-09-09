package com.beikai.springboottestdemo.designPattern.iteratePattern.basic;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/5
 * Time: 6:04 下午
 * Description: 菜 具体信息类
 */
@Data
public class MenuItem {
    // 菜名
    private String name;
    // 描述
    private String description;
    // 是否是素菜
    private Boolean isVegetarian;
    // 价格
    private Double price;

    public MenuItem(String name, String description, Boolean isVegetarian, Double price) {
        this.name = name;
        this.description = description;
        this.isVegetarian = isVegetarian;
        this.price = price;
    }



    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isVegetarian=" + isVegetarian +
                ", price=" + price;
    }
}
