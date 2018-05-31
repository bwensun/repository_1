package com.example.demo.common.config;

import com.example.demo.common.intercepter.TestInterceptor;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 自定义静态资源配置
 *
 * @author bowensun
 * @sice 2018/5/31
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/")
                .addResourceLocations("classpath:/index/")
                .addResourceLocations("classpath:/static/")
                .setCacheControl(CacheControl.noCache());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new TestInterceptor()).addPathPatterns("/**").excludePathPatterns("");
        super.addInterceptors(registry);
    }

}
