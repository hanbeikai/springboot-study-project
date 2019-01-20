package com.beikai.springbootthread.service.impl;

import com.beikai.springbootthread.service.CreateIdService;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @ClassName UUIDServiceImpl
 * @Description TODO
 * @Author Admin
 * @Date 2019/1/16 22:18
 * @Version 1.0
 *  通过uuid 方式获取的id
 **/
@Service("uuidServiceImpl")
public class UUIDServiceImpl implements CreateIdService {


    /**
     * 通过uuid的方式获取 订单号
     */
    @Override
    public void getId() {
        System.out.println("insert into threadId(id) VALUES('"
                + UUID.randomUUID().toString().replace("-","")+"');");
    }
}
