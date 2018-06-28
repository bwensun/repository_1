package com.example.demo.web;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import com.example.demo.web.support.Result;
import com.example.demo.web.support.Success;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        logger.info("用户注册结束");
        Result result = new Success(true);
        return result;
    }

    /**
     * 登录
     *
     * @param user
     */
    @RequestMapping("login")
    public Result login(User user){
        logger.info("用户登录开始");
        userService.login(user);
        logger.info("用户登录结束");
        Result result = new Success(true);
        return result;
    }

    /**
     * 查询用户列表
     *
     * @return
     */
    @RequestMapping("findList")
    public Result findList(){
        logger.info("查询用户列表开始");
        List<User> records = userService.findList();
        logger.info("查询用户列表结束");
        Result result = new Success(records);
        return result;
    }

}
