package com.javachen.hystrix;

import com.javachen.service.GreetingService;
import feign.hystrix.FallbackFactory;

/**
 * @author june
 * @date 2019-05-07 12:01
 */
public class GreetingServiceFallbackFactory implements FallbackFactory<GreetingService> {
    @Override
    public GreetingService create(Throwable throwable) {
        return new GreetingService() {
            @Override
            public String greeting(String username) {
                return "Hello User!";
            }
        };
    }
}