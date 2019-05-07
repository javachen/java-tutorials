package com.javachen.service;

import com.javachen.hystrix.GreetingServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "producer", fallback = GreetingServiceFallback.class)
public interface GreetingService {
    @GetMapping("/greeting/{username}")
    public String greeting(@PathVariable("username") String username);
}
