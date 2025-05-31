package com.ryan.medical.utils.login;

import com.alibaba.fastjson.JSONObject;
import com.ryan.medical.pojo.Result;
import com.ryan.medical.utils.jwt.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

//"!方法二!":定义了一个 Spring MVC 的拦截器（LoginCheckInterceptor）以及一个配置类（WebConfig）来注册和配置该拦截器
//功能是检查每个请求的 token，并确保用户已经登录
//用于处理 "!Spring MVC!" 控制器的请求，适用于 Spring 的处理链

@Component
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {

        //获取url
        String url=req.getRequestURI().toString();
        log.info("url是：{}",url);
        //1.看看有没login
        if(url.contains("/login")){

            return true;//放行不检查token
        }
        //2.现在不是login，获取token

//        String header = req.getHeader("Authorization");
        String header = req.getHeader("token");
        String token = null;

        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7).trim();
        }


        log.info("token is :{}",token);
        //3.判断token是不是空,空情况
        if(!StringUtils.hasLength(token)){
            log.info("Token不存在");
            //报错，给Result对象
            Result ans=Result.error("没有登录！");
            //将对象转换成json格式
            String json = JSONObject.toJSONString(ans);
            //将响应设置格式
            res.setContentType("application/json;charset=utf-8");
            //把json设置给响应
            res.getWriter().write(json);
            return false;
        }
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            log.info("token失效");
            //报错，给Result对象
            Result ans=Result.error("没有登录！");
            //将对象转换成json格式
            String json = JSONObject.toJSONString(ans);
            //将响应设置格式
            res.setContentType("application/json;charset=utf-8");
            //把json设置给响应
            res.getWriter().write(json);
            return false;
        }
        //最后token有效
        return true;
    }
}

