package com.example.demo.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * description
 *
 * @author bowensun
 * @sice 2018/6/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SendEmailTest {

    @Autowired
    SendEmail sendEmail;

    @Test
    public void sendSimpleEmail() {
        String to = "twf1040@foxmail.com";
        String subject = "测试邮件";
        String content = "这是一条测试邮件，不必回复。。。";
        String poem = "人生若只如初见 何事秋风悲画扇 等闲变却故人心 却道故人心易变 骊山语罢清宵半 泪雨零铃终不怨 何如薄幸锦衣郎 比翼连枝当日愿";
        String [] poems = poem.split(" ");
        for (int i = 0; i < 8; i++) {
            sendEmail.sendSimpleEmail(to, subject, content+"\n"+poems[i]);
        }
    }

    @Test
    public void sendHtmlMessage() {
        String to = "twf1040@foxmail.com";
        String to2 = "2930422236@qq.com";
        String subject = "测试邮件";
        String content = "<html lang=\"en\">\n" +
                "<body>\n" +
                "\t<h3>Hello World</h3>\n" +
                "\t<span>这是一个测试邮件，不必回复。。。</span>\n" +
                "</body>\n" +
                "</html>\t";
        sendEmail.sendHtmlMessage(to2, subject, content);
    }

    @Test
    public void sendMailWithAttachments() {
        String to = "twf1040@foxmail.com";
        String to2 = "2930422236@qq.com";
        String subject = "测试邮件";
        String content = "这是一条测试邮件，不必回复。。。";
        String filePath = "C:/Users/Administrator/Pictures/Saved Pictures/5.png";
        sendEmail.sendMailWithAttachments(to, subject, content, filePath);
    }

    @Test
    public void sendMailWithStaticResource() {
        String to = "twf1040@foxmail.com";
        String to2 = "18225862715@163.com";
        String subject = "测试邮件";
        String rscId = "name";
        String content = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "\t<meta charset=\"UTF-8\">\n" +
                "\t<title>测试邮件</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\t<h3>Hello World</h3>\n" +
                "\t<span>这是一个测试邮件，不必回复。。。</span>\n" +
                "<img src=\'cid:" + rscId + "\' >"+
                "</body>\n" +
                "</html>\t";
        String content2 = "<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String filePath = "C:/Users/Administrator/Pictures/Saved Pictures/5.png";
        sendEmail.sendMailWithStaticResource(to2, subject, content2, filePath, rscId);

    }
}