package com.example.demo.service.impl;

import com.example.demo.common.exception.ServiceException;
import com.example.demo.domain.User;
import com.example.demo.repository.UserDao;
import com.example.demo.service.UserService;
import com.example.demo.web.support.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

/**
 * 用户
 *
 * @author bowensun
 */
@Service
@Transactional()
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
        userDao.insert(user);
    }

    /**
     * 用户登录
     *
     * @param user
     */
    @Override
    public void login(User user) {
        User result = userDao.findByUserNameAndPassword(user.getUserName(), user.getPassword());
        if(result == null){
            throw new ServiceException("用户不存在",ErrorCode.USER_NOT_EXISTS);
        }
    }
}
