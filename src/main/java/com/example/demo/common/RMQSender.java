package com.example.demo.common;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * description
 *
 * @author bowensun
 * @since 2018/7/5
 */
@Component
public class RMQSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sender(){
        System.out.println("进入sender");
        String content = "rmq1" + "Sender:这是一条测试消息";
        this.amqpTemplate.convertAndSend("rmq1", content);
    }
}
