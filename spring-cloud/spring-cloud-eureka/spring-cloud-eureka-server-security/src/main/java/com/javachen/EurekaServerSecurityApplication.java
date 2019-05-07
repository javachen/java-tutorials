package com.javachen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerSecurityApplication.class, args);
    }
}