package com.cyw.info_submit.config;

import com.cyw.info_submit.Interceptor.RequestLoggerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.Arrays;
import java.util.List;

@Configuration
public class HandlerInterceptorConf implements WebMvcConfigurer {

    /**
     * 不拦截项目中对静态资源的请求
     *      {跳转到的登录页面、登录方法不拦截}
     */
    private static final List<String> EXCLUDE_PATH =
            Arrays.asList("/css/**", "/js/**", "/image/**", "/", "/login");

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器
        registry.addInterceptor(new RequestLoggerInterceptor())         //请求日志拦截器
                //需要拦截的路径(拦截所有请求)
                .addPathPatterns("/**")
                .excludePathPatterns(EXCLUDE_PATH);                     //不需要拦截的路径
    }
}
