package com.example.demo.web.support;

/**
 * 正确的结果
 *
 * @author bowensun
 */
public class Success implements Result{
    public int code = 200;

    public Object data;

    public String message = "成功";

    public Success(Object data) {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
