package com.javachen.springboot.security.custom_login.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index");
        registry.addViewController("/index").setViewName("index");

        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/user/index").setViewName("user/index");
    }
}
