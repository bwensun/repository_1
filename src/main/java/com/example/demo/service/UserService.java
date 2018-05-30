package com.example.demo.service;

import com.example.demo.domain.User;

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
}
