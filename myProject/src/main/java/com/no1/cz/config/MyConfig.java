//package com.no1.cz.config;
//
//import com.no1.cz.interceptor.MyInterceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class MyConfig implements WebMvcConfigurer {
//
//    // 注册拦截器
//    @Bean
//    public MyInterceptor myInterceptor(){
//        return new MyInterceptor();
//    }
//
//    // 定义拦截规则，将拦截器添加到Spring MVC拦截器链
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(myInterceptor()).addPathPatterns("/*").excludePathPatterns("/index");
//    }
//}
