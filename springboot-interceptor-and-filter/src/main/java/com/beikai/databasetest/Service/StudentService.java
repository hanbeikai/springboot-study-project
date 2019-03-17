package com.beikai.databasetest.Service;

import com.beikai.databasetest.model.Student;

/**
 * @ClassName StudentService
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/17 0:59
 * @Version 1.0
 **/

public interface StudentService {

    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}
