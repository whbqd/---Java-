package com.dy.filter;

import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author: ZT
 * Date: 2020/5/23 18:26
 * Version 1.0
 */
//@Configuration
//public class CrosFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletResponse res = (HttpServletResponse) servletResponse;
//        //*号表示对所有请求都允许跨域访问
//        res.addHeader("Access-Control-Allow-Origin", "*");
//        res.addHeader("Access-Control-Allow-Methods", "*");
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//}
