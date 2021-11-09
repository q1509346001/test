//package com.no1.cz.interceptor;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Slf4j
//public class MyInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        log.info("MyInterceptor的preHandle ran...");
//        // return false; // true表示不拦截，谁都允许通过，默认是false，不放行。
//        return true; // 为方便后续代码编写测试，暂且设置为全部放行，后续根据实际情况来考量拦截规则
//     }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        log.info("MyInterceptor的postHandle ran...");
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        log.info("MyInterceptor的afterCompletion ran...");
//    }
//}
