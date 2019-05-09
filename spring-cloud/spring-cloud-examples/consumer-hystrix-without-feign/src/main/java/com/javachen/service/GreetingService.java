package com.javachen.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GreetingService {
    public String greeting(String username) {
        return new RestTemplate().getForObject("http://localhost:9090/greeting/{username}", String.class, username);
    }
}
