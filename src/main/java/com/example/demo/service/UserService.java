package com.example.demo.service;

import com.example.demo.domain.User;

import java.util.List;

/**
 * 用户
 *
 * @author bowensun
 */
public interface UserService {
    /**
     * 用户注册
     *
     * @param user
     */
    void register(User user);

    /**
     * 用户登录
     *
     * @param user
     */
    void login(User user);

    /**
     * 查询用户列表
     *
     * @return
     */
    List<User> findList();
}
