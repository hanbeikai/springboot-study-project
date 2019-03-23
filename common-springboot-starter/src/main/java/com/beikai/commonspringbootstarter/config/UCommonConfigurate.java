package com.beikai.commonspringbootstarter.config;

import com.beikai.commonspringbootstarter.properties.CommonProperties;
import com.beikai.service.CommonService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName UCommonConfigurate
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/21 7:52
 * @Version 1.0
 *
 *  EnableConfigurationProperties  引入参数类
 **/
@Configuration
@EnableConfigurationProperties(CommonProperties.class)
public class UCommonConfigurate {

    @Bean
    public CommonService get(CommonProperties properties){
        CommonService commonService = new CommonService();
        commonService.setName(properties.getName());
        commonService.setTime(properties.getTime());
        return commonService;
    }
}
