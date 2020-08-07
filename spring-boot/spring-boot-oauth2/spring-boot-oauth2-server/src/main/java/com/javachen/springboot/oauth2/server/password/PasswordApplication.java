package com.javachen.springboot.oauth2.server.password;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@EnableAuthorizationServer
@SpringBootApplication
public class PasswordApplication {
    public static void main(String[] args) {
        SpringApplication.run(PasswordApplication.class, args);
    }
}
