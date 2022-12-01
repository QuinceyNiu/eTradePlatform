package com.comz.store.config;

import com.comz.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

@Configuration //加载当前的拦截器并进行注册
public class LoginInterceptorConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //创建自定义拦截器
        LoginInterceptor interceptor = new LoginInterceptor();

        //白名单，放在List中
        ArrayList<String> patterns = new ArrayList<>();
        patterns.add("/assets/css/**");
        patterns.add("/assets/images/**");
        patterns.add("/assets/fonts/**");
        patterns.add("/assets/js/**");
        patterns.add("/sign-in.html");
        patterns.add("/sign-up.html");
        patterns.add("/index.html");
        patterns.add("/shop.html");
        patterns.add("/forgot-password.html");
        patterns.add("/contact.html");
        patterns.add("/about-us.html");
        patterns.add("/customers/reg");
        patterns.add("/customers/login");
        patterns.add("/states/**");

        //完成拦截器注册
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(patterns);
    }
}
