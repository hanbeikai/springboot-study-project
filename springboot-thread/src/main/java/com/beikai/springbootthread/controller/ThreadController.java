package com.beikai.springbootthread.controller;

import com.beikai.springbootthread.service.IThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ThreadController
 * @Description TODO
 * @Author Admin
 * @Date 2019/1/6 23:56
 * @Version 1.0
 **/
@RequestMapping("/thread")
@RestController
public class ThreadController {

    @Autowired
    private IThreadService threadService;


}
