package com.beikai.MutilOfDruid.service;


import com.beikai.MutilOfDruid.pojo.User;

import java.util.List;

/**
 * 
* Title: UserService
* Description: 
* 用户接口 
* Version:1.0.0  
* @author pancm
* @date 2018年3月19日
 */
public interface UserService extends BaseService<User>{

    List<User> getAll();

}
