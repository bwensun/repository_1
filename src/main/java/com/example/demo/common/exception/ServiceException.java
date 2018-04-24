package com.example.demo.common.exception;

import com.example.demo.web.support.ErrorCode;

/**
 * 业务异常类
 *
 * @author bowensun
 * @date 2018/4/24
 */
public class ServiceException extends RuntimeException{

    public ErrorCode errorCode;

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public ServiceException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public ServiceException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
