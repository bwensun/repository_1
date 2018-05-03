package com.example.demo.repository;

import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {

    /**
     * 新增用户
     *
     * @param user
     */
    void insertSelective(User user);

    /**
     * 登录
     *
     * @param userName
     * @param password
     * @return
     */
    User findByUserNameAndPassword(String userName, String password);
}
