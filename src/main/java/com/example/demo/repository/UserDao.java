package com.example.demo.repository;

import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * 查询列表
     *
     * @return
     */
    List<User> findList();


    void update(@Param("user") User user);
}
