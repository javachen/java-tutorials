package com.javachen.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GreetingService {
    public String greeting(String username) {
        return new RestTemplate().getForObject("http://localhost:8080/greeting/{username}", String.class, username);
    }

    public String greeting2(String username){
        return greeting(username);
    }
}
