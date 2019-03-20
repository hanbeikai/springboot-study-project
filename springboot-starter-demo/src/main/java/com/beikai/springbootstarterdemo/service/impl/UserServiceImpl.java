package com.beikai.springbootstarterdemo.service.impl;

import com.beikai.springbootstarterdemo.dao.IUserDao;
import com.beikai.springbootstarterdemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/20 21:50
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    /**
     * 获取用户信息
     * @param id
     * @return
     */
    @Override
    public Object get(String id) {

        return this.userDao.getUser(Integer.parseInt(id));
    }
}
