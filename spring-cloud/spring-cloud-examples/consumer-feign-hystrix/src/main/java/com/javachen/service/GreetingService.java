package com.javachen.service;

import com.javachen.config.FeignConfig;
import com.javachen.hystrix.GreetingServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "rest-producer", configuration = FeignConfig.class, fallback = GreetingServiceFallback.class)
//@FeignClient(name = "rest-producer",fallbackFactory = GreetingServiceFallbackFactory.class)
public interface GreetingService {
    @RequestMapping("/greeting/{username}")
    public String greeting(String username);
}
