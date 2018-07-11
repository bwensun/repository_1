package com.example.demo.web.support;

/**
 * 错误码
 *
 * @author bowensun
 */
public enum ErrorCode {
    INTERNAL_ERROR(500,"代码有问题"),
    USER_NOT_EXISTS(001,"用户不存在"),
    FILE_NOT_EXISTS(002, "文件不存在")
    ;
    public int code;
    public String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
