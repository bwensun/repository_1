package com.example.demo.web;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import com.example.demo.web.support.Result;
import com.example.demo.web.support.Success;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 *
 * @author bowensun
 */
@RestController
@RequestMapping("user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 注册
     *
     * @param user
     */
    @RequestMapping("register")
    public Result register(User user){
        logger.info("用户注册开始");
        userService.register(user);
        logger.info("用户注册开始");
        Result result = new Success(true);
        return result;
    }
}
