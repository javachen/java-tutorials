package com.javachen.rss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.security.RolesAllowed;

@SpringBootApplication
@ComponentScan(basePackages = "com.javachen.rss")
public class RssApp {

    @RolesAllowed("*")
    public static void main(String[] args) {
        SpringApplication.run(RssApp.class, args);
    }

}
