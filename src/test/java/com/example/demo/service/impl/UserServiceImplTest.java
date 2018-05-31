package com.example.demo.service.impl;

import com.example.demo.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * 单元测试
 *
 * @author bowensun
 * @sice 2018/5/31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void test(){
        User user = new User("12","12","123","1234");
        userService.register(user);
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("测试开始之前");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("测试开始之后");
    }
}