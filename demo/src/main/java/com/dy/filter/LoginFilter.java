package com.dy.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dy.packageEntity.Result;
import com.fasterxml.jackson.core.JsonGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Author: ZT
 * Date: 2020/5/22 21:28
 * Version 1.0
 * @author 18086
 */
@WebFilter(urlPatterns = {"/user/view/*","/user/leaving/view/*"}, filterName = "LoginFilter")
public class LoginFilter implements Filter {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //Filter过滤器跨域处理
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
//        获取token
        String token = request.getHeader("token");
        System.out.println("filter :" + token);
        //如果token为null替换为空字符串“”
        token = token == null ? "" : token;
        //查询token在redis的剩余时间
        Long expire = redisTemplate.getExpire(token);
//        System.out.println(expire);
        if (expire > 0) {
            //重置token到30分钟
            redisTemplate.expire(token, 30L, TimeUnit.MINUTES);
            //放行
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            //响应json
            response.setContentType("json/text;charset=utf-8");
            response.getWriter().write(JSONObject.toJSONString(new Result(null, "未登录", 103)));
        }
    }

    @Override
    public void destroy() {}
}
