package com.beikai.MutilOfDruid.dao.master;

import com.beikai.MutilOfDruid.dao.BaseDao;
import com.beikai.MutilOfDruid.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
* Title: UserDao
* Description:
* 用户数据接口 
* Version:1.0.0  
* @author pancm
* @date 2018年1月9日
 */
@Mapper
public interface UserDao extends BaseDao<User>{

    List<User> getAll();

}
