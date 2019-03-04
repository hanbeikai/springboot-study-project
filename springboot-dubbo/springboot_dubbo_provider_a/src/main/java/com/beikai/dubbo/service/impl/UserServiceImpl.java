package com.beikai.dubbo.service.impl;


import com.alibaba.dubbo.rpc.RpcContext;
import com.beikai.dubbo.dao.UserDao;
import com.beikai.dubbo.entity.User;
import com.beikai.dubbo.service.UserService;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Beikai.Han on 2019/3/4.
 */
@Service
public class UserServiceImpl implements UserService{

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return userDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        return userDao.insert(record);
    }

    @Override
    public User selectByPrimaryKey(Long id) {
        // 本端是否为提供端,这里会返回true
        boolean providerSide = RpcContext.getContext().isProviderSide();
        // 获取调用方ip的地址
        String remoteHost = RpcContext.getContext().getRemoteHost();
        //  获取当前服务配置信息,所有配置信息都将转换为url的参数
        String s = RpcContext.getContext().getUrl().toFullString();

        logger.info("{} {} {}",providerSide,remoteHost,s);


        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public List<User> selectAll() {
        return userDao.selectAll();
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userDao.updateByPrimaryKey(record);
    }

    /**
     * 接口新增一个方法测试
     * @return
     */
    @Override
    public Map<String, Object> addMethod() {
        Map<String,Object> result = Maps.newHashMap();
        result.put("attachment",true);
        String count = RpcContext.getContext().getAttachment("count");
        result.put("count",count);

        // 区分 a 和 b 的参数

        result.put("more","providerA");

        return result;
    }
}
