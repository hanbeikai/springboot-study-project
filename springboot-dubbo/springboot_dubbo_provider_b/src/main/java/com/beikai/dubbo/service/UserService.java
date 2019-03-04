package com.beikai.dubbo.service;

import com.beikai.dubbo.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Beikai.Han on 2019/3/4.
 */
public interface UserService {

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    Map<String, Object> addMethod();

}
