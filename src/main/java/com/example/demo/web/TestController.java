package com.example.demo.web;

import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 *
 * @author bowensun
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("helloWorld")
    public String helloWorld(){
        return "helloWorld";
    }

}
