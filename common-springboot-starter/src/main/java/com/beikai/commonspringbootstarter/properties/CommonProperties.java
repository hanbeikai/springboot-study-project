package com.beikai.commonspringbootstarter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName CommonProperties
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/21 7:54
 * @Version 1.0
 * 参数 配置类
 **/
@ConfigurationProperties(prefix = "com.beikai.common")
public class CommonProperties {
    /**
     * 服务名
     */
    private String name;
    /**
     * 服务时长
     */
    private int time;

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
