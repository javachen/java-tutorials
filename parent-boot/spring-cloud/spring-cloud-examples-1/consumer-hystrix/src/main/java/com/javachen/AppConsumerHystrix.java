package com.javachen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix
public class AppConsumerHystrix {
    public static void main(String[] args) {
        SpringApplication.run(AppConsumerHystrix.class, args);
    }
}
