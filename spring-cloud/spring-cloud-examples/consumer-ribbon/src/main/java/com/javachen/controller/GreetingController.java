package com.javachen.controller;

import com.javachen.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    @Autowired
    private GreetingService greetingService;

    @RequestMapping("/greeting/{username}")
    public String greeting(@PathVariable("username") String username) {
        return greetingService.greeting(username);
    }
}
