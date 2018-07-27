package com.example.demo.service.impl;

import com.example.demo.domain.User;
import com.example.demo.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 异步调用类
 *
 * @author bowensun
 * @since 2018/7/27
 */
@Component
@Transactional( rollbackFor = Throwable.class)
public class AsyncTask {

    @Autowired
    private UserDao userDao;

    @Async
    public void task1(){
        System.out.println("进入task1...");
        List<User> list = userDao.findList();
        list.stream().forEach(x -> System.out.println(x));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task1线程执行完毕。。");
    }

    @Async(value = "taskExecutor")
    public void task2(){
        System.out.println("task2....");
    }

    @Async(value = "taskExecutor")
    public void task3(){
        System.out.println("task3....");
    }

    @Async(value = "taskExecutor")
    public void task4(){
        System.out.println("task4....");
    }
}
