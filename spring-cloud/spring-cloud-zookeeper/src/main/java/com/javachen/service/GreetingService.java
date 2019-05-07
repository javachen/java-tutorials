package com.javachen.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "producer")
public interface GreetingService {
    @GetMapping("/greeting/{username}")
    public String greeting(@PathVariable("username") String username);
}
