package com.beikai.dao.impl;

import com.beikai.dao.UserDao;
import com.beikai.vo.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    @Resource
    JdbcTemplate jdbcTemplate;
    /*通过用户名获取用户密码*/
    public User getPasswordByUsername(String username) {
        String sql = "select * from users where username = ?";
        List<User> users = jdbcTemplate.query(sql, new String[]{username}, new RowMapper<User>() {

            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        });
        if (CollectionUtils.isEmpty(users)){
            return null;
        }
        return users.get(0);
    }

    public List<String> getRoleByUsername(String username) {
        String sql ="select role_name from user_roles where username = ?";
        List<String> roles = jdbcTemplate.query(sql, new String[]{username}, new RowMapper<String>() {

            public String mapRow(ResultSet resultSet, int i) throws SQLException {

                return resultSet.getString("role_name");
            }
        });

        return roles;
    }

    public User getSaltByUsername(String username) {
        String sql = "select * from users where userName = ?";
        List<User> users = jdbcTemplate.query(sql, new String[]{username}, new RowMapper<User>() {

            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setSalt(resultSet.getString("salt"));
                return user;
            }
        });

        if (CollectionUtils.isEmpty(users)){
            return null;
        }
        return users.get(0);
    }
}
