package com.javachen.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {
    @Bean
    @LoadBalanced //给RestTemplate添加了拦截器，修改了代码
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
