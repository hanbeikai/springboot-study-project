package com.beikai.springboottestdemo.aspect.service;

import org.springframework.stereotype.Component;

/**
 * @ClassName AopService
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/26 20:58
 * @Version 1.0
 **/
@Component
public class AopService {
    public void query(){
        System.out.println("spring ---- init --- ");
    }
}
