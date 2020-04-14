package com.javachen.web.servlet.normal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ServletComponentScan("com.javachen.web.servlet.normal")
public class SpringBootNormalApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootNormalApp.class, args);
    }

    @Bean
    public ServletRegistrationBean getServletRegistrationBean() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new HelloServlet());
        bean.addUrlMappings("/hello");
        return bean;
    }

    @Bean
    public FilterRegistrationBean getFilterRegistrationBean() {
        FilterRegistrationBean bean = new FilterRegistrationBean(new HelloFilter());
        bean.addUrlPatterns("/hello");
        return bean;
    }

    @Bean
    public ServletListenerRegistrationBean<AttrListener> getServletListenerRegistrationBean() {
        ServletListenerRegistrationBean<AttrListener> bean = new ServletListenerRegistrationBean(new AttrListener());
        return bean;
    }
}
