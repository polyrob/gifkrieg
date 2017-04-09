//package com.gifkrieg.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * Created by robbie on 4/8/17.
// */
//@Configuration
//public class WebMvcConfig extends WebMvcConfigurerAdapter {
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//        registry.addInterceptor(new RestInInterceptor());
//
//
//    }
//
//    static class RestInInterceptor implements HandlerInterceptor {
//        Logger log = LoggerFactory.getLogger(getClass().getName());
//
//        @Override
//        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//            log.debug("*** ### *** RestInInterceptor preHandle");
//            log.debug(request.getAuthType());
//            log.debug(request.getRemoteUser());
//            log.debug(String.valueOf(request.getUserPrincipal()));
//            return false;
//        }
//
//        @Override
//        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//
//        }
//
//        @Override
//        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//        }
//    }
//}
//
//
//
