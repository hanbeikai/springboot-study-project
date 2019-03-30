package com.beikai.dao;

import com.beikai.vo.User;

import java.util.List;

public interface UserDao {

    public User getPasswordByUsername(String username);

    public List<String> getRoleByUsername(String username);

    public User getSaltByUsername(String username);
}
