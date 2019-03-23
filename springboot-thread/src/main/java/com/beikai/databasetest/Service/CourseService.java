package com.beikai.databasetest.Service;

import com.beikai.databasetest.model.Course;

public interface CourseService {
    int deleteByPrimaryKey(Integer cId);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Integer cId);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);
}
