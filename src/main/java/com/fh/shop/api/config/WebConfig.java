package com.fh.shop.api.config;

import com.fh.shop.api.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class WebConfig implements WebMvcConfigurer {

    public void  addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/api/**");
    }


    //相当于<bean id="loginInterceptor" class="com.fh.shop.api.interceptor.LoginInterceptor"></bean>
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }



}
