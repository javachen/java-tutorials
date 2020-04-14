package com.javachen.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "producer", url = "http://localhost:8000", fallback = GreetingService.GreetingClientFallback.class)
public interface GreetingService {
    @RequestMapping("/greeting/{username}")
    public String greeting(String username);

    public static class GreetingClientFallback implements GreetingService {
        @Override
        public String greeting(@PathVariable("username") String username) {
            return "Hello User!";
        }
    }
}
