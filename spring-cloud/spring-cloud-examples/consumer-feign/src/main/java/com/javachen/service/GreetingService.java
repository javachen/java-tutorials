package com.javachen.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "provider") //provider为注册中心上服务方的应用名称
public interface GreetingService {
    @GetMapping("/greeting/{username}")
    public String greeting(@PathVariable("username") String username);
}
