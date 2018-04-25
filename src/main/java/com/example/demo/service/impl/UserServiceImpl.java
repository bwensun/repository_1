package com.example.demo.service.impl;

import com.example.demo.domain.User;
import com.example.demo.repository.UserDao;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 用户注册
     *
     * @param user
     */
    @Override
    public void register(User user) {
        User user2 = new User("孙博文","霸道学长孙博文","17612587856","285413");
        userDao.save(user2);
    }
}
