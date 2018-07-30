package com.example.demo.common;

import java.lang.annotation.*;

/**
 * 防止表单重复提交注解类
 *
 * @author bowensun
 * @since 2018/7/30
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface SubmitToken {

    boolean save() default true;
}
