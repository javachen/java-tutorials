package com.javachen.springboot.security.auto_config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
//        SecurityAutoConfiguration.class
})
public class AutoConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(AutoConfigApplication.class, args);
    }
}
