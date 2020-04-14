package com.javachen.hystrix;

import com.javachen.service.GreetingService;
import org.springframework.stereotype.Component;

public class GreetingServiceFallback implements GreetingService {
    public String greeting(String username) {
        return "Hello User!";
    }
}