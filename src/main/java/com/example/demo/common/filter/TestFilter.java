package com.example.demo.common.filter;

import ch.qos.logback.core.net.SyslogOutputStream;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class TestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        System.out.println(httpServletRequest.getContextPath());
        filterChain.doFilter(httpServletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
