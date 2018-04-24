package com.example.demo.web.support;

/**
 * 错误码
 *
 * @author bowensun
 */
public enum ErrorCode {
    INTERNAL_ERROR(500,"代码有问题"),
    ;
    public int code;
    public String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
