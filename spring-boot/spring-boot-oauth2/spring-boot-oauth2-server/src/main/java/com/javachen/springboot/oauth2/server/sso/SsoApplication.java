package com.javachen.springboot.oauth2.server.sso;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
public class SsoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SsoApplication.class, args);
    }
}
