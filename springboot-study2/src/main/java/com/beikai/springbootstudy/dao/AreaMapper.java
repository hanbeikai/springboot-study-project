package com.beikai.springbootstudy.dao;

import com.beikai.springbootstudy.entity.Area;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Mapper  dao层接口使用的注解
 */
@Mapper
public interface AreaMapper {
    int deleteByPrimaryKey(Integer areaId);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Integer areaId);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);

    public List<Area> getAreaList();
}