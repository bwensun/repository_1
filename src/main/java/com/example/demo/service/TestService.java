package com.example.demo.service;

import com.example.demo.domain.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 测试
 *
 * @author bowesun
 */
public interface TestService {

    /**
     * 文件上传
     *
     * @param file
     */
    void upload(MultipartFile file);

    /**
     * 文件下载
     *
     * @param response
     * @param request
     * @param filepath
     */
    void download(HttpServletResponse response, HttpServletRequest request, String filepath);

    /**
     * 异步调用测试开始
     *
     *
     */
    void taskTask();
}
