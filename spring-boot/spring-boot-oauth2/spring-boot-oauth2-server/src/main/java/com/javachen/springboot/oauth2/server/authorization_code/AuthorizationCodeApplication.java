package com.javachen.springboot.oauth2.server.authorization_code;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@EnableAuthorizationServer
@SpringBootApplication
public class AuthorizationCodeApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthorizationCodeApplication.class, args);
    }
}
