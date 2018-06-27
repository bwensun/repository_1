package com.example.demo.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

/**
 * 发送邮件
 *
 * @author bowensun
 * @since 2018/6/22
 */
@Component
public class SendEmail {

    private Logger logger = LoggerFactory.getLogger(SendEmail.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送简单邮件
     *
     * @param to 收件人地址
     * @param subject 主题
     * @param content 内容
     */
    public void sendSimpleEmail(String to, String subject, String content){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        try {
            mailSender.send(simpleMailMessage);
            logger.info("============邮件正在发送=========");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("===========邮件发送异常，发送失败=======");
        }
    }

    /**
     * 发送html邮件
     *
     * @param to 收件人地址
     * @param subject 主题
     * @param content 内容
     */
    public void sendHtmlMessage(String to, String subject, String content){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content);
            mailSender.send(mimeMessage);
            logger.info("============邮件正在发送=========");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("===========邮件发送异常，发送失败=======");
        }
    }

    /**
     * 发送带附件的邮件
     *
     * @param to 收件人地址
     * @param subject 主题
     * @param content 内容
     * @param filePath 附件地址
     */
    public void sendMailWithAttachments(String to, String subject, String content, String filePath){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content);

            FileSystemResource resource = new FileSystemResource(filePath);
            String filename = resource.getFilename();
            mimeMessageHelper.addAttachment(filename, resource);
            mailSender.send(mimeMessage);
            logger.info("============邮件正在发送=========");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("===========邮件发送异常，发送失败=======");
        }
    }

    /**
     * 发送带静态资源的邮件
     *
     * @param to 收件人地址
     * @param subject 主题
     * @param content 内容
     * @param resourcePath 静态资源地址
     * @param resourceName 静态资源名称
     */
    public void sendMailWithStaticResource(String to, String subject, String content, String resourcePath, String resourceName){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content);

            FileSystemResource resource = new FileSystemResource(resourcePath);
            mimeMessageHelper.addInline(resourceName, resource);
            mailSender.send(mimeMessage);
            logger.info("============邮件正在发送=========");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("===========邮件发送异常，发送失败=======");
        }
    }
}
