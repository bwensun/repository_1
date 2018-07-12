package com.example.demo.service.impl;


import com.example.demo.common.Constant;
import com.example.demo.common.exception.ServiceException;
import com.example.demo.common.properties.SystemProperties;
import com.example.demo.service.TestService;
import com.example.demo.web.support.ErrorCode;
import org.apache.commons.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TestServiceImpl implements TestService {

    static Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

    @Autowired
    private SystemProperties systemProperties;

    /**
     * 文件上传
     *
     * @param file
     */
    @Override
    public void upload(MultipartFile file) {
        if (file == null) {
            throw new ServiceException(ErrorCode.FILE_NOT_EXISTS);
        }
        String originalFilename = file.getOriginalFilename();
        String uploadFileLocation = systemProperties.getUploadFileLocation();
        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        File destFile = generateFile(uploadFileLocation +"/"+ time + originalFilename);
        System.out.println(uploadFileLocation);
        try {
            file.transferTo(destFile);
        } catch (IOException e) {
            logger.error(e.getCause().toString());
        }
    }

    /**
     * 文件下载
     *
     * @param response
     * @param filepath
     */
    @Override
    public void download(HttpServletResponse response, HttpServletRequest request, String filepath) {
        FileInputStream fileInputStream = null;
        ServletOutputStream outputStream = null;
        File file = new File(filepath);
        if (file == null) {
            throw new ServiceException(ErrorCode.FILE_NOT_EXISTS);
        }
        String filename = recodingFileName(file.getName(), request);
        response.setContentType(Constant.contentType.json.getType());
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename*=utf-8'zh_cn'" + filename);
        //response.setHeader("Content-Disposition", "attachment; filename=" + filename);
        try {
            fileInputStream = new FileInputStream(file);
            outputStream = response.getOutputStream();
            IOUtils.copy(fileInputStream,outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //重新编码解决中文乱码问题
    private String recodingFileName(String name, HttpServletRequest request) {
        try {
            String header = request.getHeader("User-Agent");
            System.out.println(header);
            if (request.getHeader("User-Agent").toUpperCase().indexOf("FIREFOX") > 0) {
                name = "=?UTF-8?B?" + (new String(Base64.encodeBase64(name.getBytes("UTF-8")))) + "?=";
            }else {
                name = URLEncoder.encode(name, "UTF-8");
            }
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return name;
    }

    //文件不存在创建文件和目录
    private File generateFile(String uploadFileLocation) {
        File file = new File(uploadFileLocation);
        System.out.println(file.getParentFile().getName());
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
}
