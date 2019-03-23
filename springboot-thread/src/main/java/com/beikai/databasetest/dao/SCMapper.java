package com.beikai.databasetest.dao;

import com.beikai.databasetest.model.SC;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SCMapper {
    int deleteByPrimaryKey(Integer scId);

    int insert(SC record);

    int insertSelective(SC record);

    SC selectByPrimaryKey(Integer scId);

    int updateByPrimaryKeySelective(SC record);

    int updateByPrimaryKey(SC record);
}