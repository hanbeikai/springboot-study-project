package com.beikai.databasetest.Service;

import com.beikai.databasetest.model.SC;

public interface SCService {
    int deleteByPrimaryKey(Integer scId);

    int insert(SC record);

    int insertSelective(SC record);

    SC selectByPrimaryKey(Integer scId);

    int updateByPrimaryKeySelective(SC record);

    int updateByPrimaryKey(SC record);
}
