package com.javachen.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class GreetingService {
    private String REST_URL_PREFIX = "http://PROVIDER";

    @Autowired
    private RestTemplate restTemplate;

    public String greeting(String username) {
        return restTemplate.getForObject(REST_URL_PREFIX + "/greeting/{username}", String.class, username);
    }
}
