package com.example.demo.common.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 系统属性配置
 *
 * @author bowensun
 * @since 2018/7/11
 */
@Component
public class SystemProperties {
    @Value("D:/user/local/uploadFile")
    private String uploadFileLocation;

    public String getUploadFileLocation() {
        return uploadFileLocation;
    }

    public void setUploadFileLocation(String uploadFileLocation) {
        this.uploadFileLocation = uploadFileLocation;
    }
}
