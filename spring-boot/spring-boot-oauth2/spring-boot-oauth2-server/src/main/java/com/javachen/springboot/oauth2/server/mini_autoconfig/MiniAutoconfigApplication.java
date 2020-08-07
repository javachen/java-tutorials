package com.javachen.springboot.oauth2.server.mini_autoconfig;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@EnableAuthorizationServer
@SpringBootApplication
public class MiniAutoconfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiniAutoconfigApplication.class, args);
    }
}
