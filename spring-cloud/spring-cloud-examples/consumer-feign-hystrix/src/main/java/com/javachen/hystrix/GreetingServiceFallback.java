package com.javachen.hystrix;

import com.javachen.service.GreetingService;
import org.springframework.stereotype.Component;

/**
 * @author june
 * @date 2019-05-07 12:01
 */
@Component
public class GreetingServiceFallback implements GreetingService {
    public String greeting(String username) {
        return "Hello User!";
    }
}