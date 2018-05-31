package com.example.demo.repository;

import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {

    /**
     * 新增用户
     *
     * @param user
     */
    void insert(User user);

    /**
     * 登录
     *
     * @param userName
     * @param password
     * @return
     */
    User findByUserNameAndPassword(String userName, String password);
}
