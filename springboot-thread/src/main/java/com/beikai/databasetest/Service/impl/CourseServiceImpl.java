package com.beikai.databasetest.Service.impl;

import com.beikai.databasetest.Service.CourseService;
import com.beikai.databasetest.dao.CourseMapper;
import com.beikai.databasetest.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName CourseServiceImpl
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/17 1:01
 * @Version 1.0
 **/
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseDao;


    @Override
    public int deleteByPrimaryKey(Integer cId) {
        return 0;
    }

    @Override
    public int insert(Course record) {
        return courseDao.insert(record);
    }

    @Override
    public int insertSelective(Course record) {
        return courseDao.insert(record);
    }

    @Override
    public Course selectByPrimaryKey(Integer cId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Course record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Course record) {
        return 0;
    }
}
