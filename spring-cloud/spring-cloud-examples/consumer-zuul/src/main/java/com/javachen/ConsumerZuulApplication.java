package com.javachen;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringCloudApplication
public class ConsumerZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerZuulApplication.class, args);
    }
}
