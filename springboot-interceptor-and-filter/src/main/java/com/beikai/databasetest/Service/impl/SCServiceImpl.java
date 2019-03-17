package com.beikai.databasetest.Service.impl;

import com.beikai.databasetest.Service.SCService;
import com.beikai.databasetest.dao.SCMapper;
import com.beikai.databasetest.model.SC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName SCServiceImpl
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/17 1:02
 * @Version 1.0
 **/
@Service
public class SCServiceImpl implements SCService {

    @Autowired
    private SCMapper scDao;

    @Override
    public int deleteByPrimaryKey(Integer scId) {
        return 0;
    }

    @Override
    public int insert(SC record) {
        return scDao.insert(record);
    }

    @Override
    public int insertSelective(SC record) {
        return 0;
    }

    @Override
    public SC selectByPrimaryKey(Integer scId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(SC record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(SC record) {
        return 0;
    }
}
