package com.example.demo.service.impl;

import com.example.demo.common.config.RedisConfig;
import com.example.demo.common.exception.ServiceException;
import com.example.demo.domain.SysArea;
import com.example.demo.domain.User;
import com.example.demo.repository.UserDao;
import com.example.demo.service.UserService;
import com.example.demo.web.support.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 用户
 *
 * @author bowensun
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisTemplate redisTemplate;



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

    /**
     * 查询用户列表
     *
     * @return
     */
    @Override
    @Cacheable(cacheNames = "users")
    public List<User> findList() {
        List<User> userList = userDao.findList();
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("bowensun", "孙博文");
        ListOperations listOperations = redisTemplate.opsForList();
        SysArea sysArea = new SysArea();
        sysArea.setAreaname("南京");
        List<SysArea> sysAreaList = new ArrayList<>();
        sysAreaList.add(sysArea);
        listOperations.leftPush("usersssss", sysAreaList);
        return userList;
    }
}
