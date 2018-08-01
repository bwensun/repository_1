package com.example.demo.common.intercepter;

import com.example.demo.common.SubmitToken;
import com.example.demo.common.exception.ServiceException;
import com.example.demo.web.support.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * token拦截器
 * 使用注解形式
 *
 * @author bowensun
 * @since 2018/7/30
 */
@Aspect
@Component
@Slf4j
public class TokenIntercepter {

    public static final String  TOKEN_KEY="token_key";

    @Pointcut("execution(public * com.example.demo.web.UserController.register(..))")
    public void webLog(){

    }

    @Before("webLog() && @annotation(token)")
    public void before(final JoinPoint joinPoint, SubmitToken token){
        System.out.println("进入before方法。。。");
        if(token != null){
            Object[] args = joinPoint.getArgs();
            HttpServletRequest request = null;
            HttpServletResponse response = null;
            for (Object arg : args) {
                if (arg instanceof HttpServletRequest) {
                    request = (HttpServletRequest) arg;
                }
                if (arg instanceof HttpServletResponse){
                    response = (HttpServletResponse) arg;
                }
            }

            boolean save = token.save();



            if (save) {
                Object result = request.getSession().getAttribute(TOKEN_KEY);
                if (null == result){
                    String uuid = UUID.randomUUID().toString().replace("-", "");
                    request.getSession().setAttribute(TOKEN_KEY, uuid);
                }else {
                    throw new ServiceException("重复请求", ErrorCode.DUPLICATED_REQUEST);
                }
            }
        }
    }
}
