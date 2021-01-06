package com.blog.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ProcessInterceptor implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(this.getClass());
      
        @Override  
        public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

            logger.info("执行拦截请求:...............{}",httpServletRequest.getMethod());
            logger.info("执行的方法为:{}",httpServletRequest.getRequestURL());
            logger.info("接收的参数为:{}",httpServletRequest.getInputStream());
            logger.info("接收长度为:{}",httpServletRequest.getContentLength());
            httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
      
            httpServletResponse.setHeader("Access-Control-Allow-Headers", "Content-Type,Content-Length, Authorization, Accept,X-Requested-With");  
      
            httpServletResponse.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");  
      
            httpServletResponse.setHeader("X-Powered-By","Jetty");  
      
      
            String method= httpServletRequest.getMethod();  
      
            if (method.equals("OPTIONS")){  
                httpServletResponse.setStatus(200);  
                return false;  
            }  
            return true;
        }  
      
        @Override  
        public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
            logger.info("执行后拦截,请求为:{},方法为{}...............",httpServletRequest.getMethod(),httpServletRequest.getRequestURL());
            logger.info("执行完成，结果为:{}",httpServletResponse.getStatus(),httpServletResponse.getHeaderNames());
        }  
      
        @Override  
        public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {  
      
        }  
    }  