package com.beikai.springbootstarterdemo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface IUserDao {

    @Select("select * from user2 where id = #{parseInt}")
    public Map<String,Object> getUser(@Param("parseInt") int parseInt);
}
