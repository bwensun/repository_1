package com.example.demo.web.support;

/**
 * 错误结果
 *
 * @author bowensun
 */
public class Error implements Result{

    public int code;

    public String message;

    public Error(ErrorCode errorCode) {
        this.code = errorCode.code;
        this.message = errorCode.message;
    }

    public Error(ErrorCode errorCode,String message){
        this.code = errorCode.code;
        this.message = message;
    }

    public Error(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
