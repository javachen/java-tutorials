package com.javachen.boot.basic.properties;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ConfigPropertiesDemoApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigPropertiesDemoApplication.class)
                .run();
    }

}
