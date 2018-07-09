package com.example.demo.common;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消息接收者
 *
 * @author bowensun
 * @since 2018/7/5
 */
@Component
@RabbitListener(queues = "rmq1")
public class RMQReceiver {

    @RabbitHandler
    public void receive(String content){
        System.out.println("开始接收。。。");
        System.out.println("receiver" + content);
    }
}
