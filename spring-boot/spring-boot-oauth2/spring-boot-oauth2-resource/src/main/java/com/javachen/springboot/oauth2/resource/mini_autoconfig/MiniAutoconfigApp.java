package com.javachen.springboot.oauth2.resource.mini_autoconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
@SpringBootApplication
public class MiniAutoconfigApp {
    public static void main(String[] args) {
        SpringApplication.run(MiniAutoconfigApp.class, args);
    }
}
