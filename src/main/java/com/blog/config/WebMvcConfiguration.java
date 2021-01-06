package com.blog.config;

import com.blog.interceptor.ProcessInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * author:xiujiang.liu
 * Date:2019/5/3
 * Time:17:49
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    ProcessInterceptor processInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.info("注册Interceptor:");
        registry.addInterceptor(processInterceptor).addPathPatterns("/**");
    }
}
