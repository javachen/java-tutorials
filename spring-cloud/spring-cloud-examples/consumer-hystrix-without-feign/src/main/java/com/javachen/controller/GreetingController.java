package com.javachen.controller;

import com.javachen.service.GreetingService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    @Autowired
    private GreetingService greetingService;

    @HystrixCommand(fallbackMethod = "defaultGreeting")
    @RequestMapping("/greeting/{username}")
    public String getGreeting(@PathVariable("username") String username) {
        return greetingService.greeting(username);
    }

    private String defaultGreeting(String username, Throwable throwable) {
        return "Hello User!";
    }
}
