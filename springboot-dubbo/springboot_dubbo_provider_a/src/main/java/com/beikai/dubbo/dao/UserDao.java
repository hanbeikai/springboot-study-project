package com.beikai.dubbo.dao;

import com.beikai.dubbo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Beikai.Han on 2019/3/4.
 */
@Repository
public interface UserDao {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}
