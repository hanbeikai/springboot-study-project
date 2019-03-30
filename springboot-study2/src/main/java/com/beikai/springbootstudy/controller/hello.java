package com.beikai.springbootstudy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName hello
 * @Description TODO
 * @Author Admin
 * @Date 2018/11/20 22:35
 * @Version 1.0
 **/
@RestController
@RequestMapping("/test")
public class hello {

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String hello(){

        return "test";
    }
}
