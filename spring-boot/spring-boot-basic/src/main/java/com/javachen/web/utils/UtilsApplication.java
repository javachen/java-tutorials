package com.javachen.web.utils;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.security.RolesAllowed;

@SpringBootApplication
@ComponentScan(basePackages = "com.javachen.web.utils")
public class UtilsApplication {

    @RolesAllowed("*")
    public static void main(String[] args) {
        SpringApplication.run(UtilsApplication.class, args);
    }

}
