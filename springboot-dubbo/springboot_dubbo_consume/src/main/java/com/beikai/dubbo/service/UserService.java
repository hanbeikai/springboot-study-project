package com.beikai.dubbo.service;

import com.beikai.dubbo.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    Map<String, Object> addMethod();
}
