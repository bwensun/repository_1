package com.example.demo.web.support;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;

@ControllerAdvice
@CrossOrigin
public class BaseController {

    private static Logger logger = LoggerFactory.getLogger(BaseController.class);


    public String handleException(Exception e, HttpServletRequest request){
        ErrorCode errorCode = ErrorCode.INTERNAL_ERROR;

        Result result = null;


    }
}
