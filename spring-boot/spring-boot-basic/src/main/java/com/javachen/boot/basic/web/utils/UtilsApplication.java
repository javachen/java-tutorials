package com.javachen.boot.basic.web.utils;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.security.RolesAllowed;

@SpringBootApplication
public class UtilsApplication {

    @RolesAllowed("*")
    public static void main(String[] args) {
        SpringApplication.run(UtilsApplication.class, args);
    }

}
