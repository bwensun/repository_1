package com.example.demo.web;


import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.demo.common.properties.TestProperties;
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
    @Autowired
    private TestProperties testProperties;

    /**
     * 测试
     *
     * @return
     */
    @RequestMapping("helloWorld")
    public String helloWorld(){
        System.out.println("进入测试");
        System.out.println(testProperties.getAuthor());
        System.out.println(testProperties.getDesc());
        return "helloWorld";
    }

}
