package com.ryan.medical.utils.login;


//"!方法二!":定义了一个 Spring MVC 的拦截器（LoginCheckInterceptor）以及一个配置类（WebConfig）来注册和配置该拦截器
//功能是检查每个请求的 token，并确保用户已经登录
//用于处理 "!Spring MVC!" 控制器的请求，适用于 Spring 的处理链
//一个配置类（WebConfig）来注册和配置该LoginCheckInterceptor拦截器

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginConfig implements WebMvcConfigurer {
    //拦截器对象
    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器对象
        registry.addInterceptor(loginCheckInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/api/login","/api/register");
    }
}
