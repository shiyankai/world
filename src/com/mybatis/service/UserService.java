package com.mybatis.service;

import com.mybatis.entities.User;
import com.mybatis.dao.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    UserDao userDao;

    public User getUserByName(String name){
        return userDao.getUser(name);
    }

    public int addUser(User user){

        return userDao.addUser(user);
    }

    @Transactional
    public int addUsers(User user1,User user2){
        int rows = 0;
        rows = userDao.addUser(user1);
        rows = userDao.addUser(user2);
        return rows;
    }
}
