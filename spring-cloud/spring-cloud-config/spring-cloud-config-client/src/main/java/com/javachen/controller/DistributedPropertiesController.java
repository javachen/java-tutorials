package com.javachen.controller;

import com.javachen.domain.MyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@EnableConfigurationProperties(MyProperties.class)
public class DistributedPropertiesController {

    @Value("${my.name}")
    String value;

    @Autowired
    private MyProperties properties;

    @GetMapping("/getConfigFromValue")
    public String getConfigFromValue() {
        return value;
    }

    @GetMapping("/getConfigFromProperty")
    public String getConfigFromProperty() {
        return properties.getName();
    }

}
