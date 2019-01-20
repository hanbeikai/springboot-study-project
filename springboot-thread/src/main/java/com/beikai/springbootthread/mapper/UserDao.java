package com.beikai.springbootthread.mapper;

import com.beikai.springbootthread.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    public List<User> getUser();

    public void addUser(User user);
}
