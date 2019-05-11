package com.javachen;

import com.javachen.config.RibbonConfig;
import com.javachen.service.GreetingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableFeignClients(clients = GreetingService.class) // 申明 GreetingService 接口作为 Feign Client 调用
@RibbonClient(name = "PROVIDER", configuration = RibbonConfig.class)
public class ConsumerFeignHystrixApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerFeignHystrixApplication.class, args);
    }
}
