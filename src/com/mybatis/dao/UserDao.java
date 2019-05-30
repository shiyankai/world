package com.mybatis.dao;

import com.mybatis.entities.User;

public interface UserDao {
    public int addUser(User user);
    public User getUser(String name);   
}  
