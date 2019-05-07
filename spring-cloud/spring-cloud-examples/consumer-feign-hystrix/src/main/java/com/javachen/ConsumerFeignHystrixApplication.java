package com.javachen;

import com.javachen.config.RibbonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableFeignClients
@RibbonClient(name = "REST-PROVIDER", configuration = RibbonConfig.class)
public class ConsumerFeignHystrixApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerFeignHystrixApplication.class, args);
    }
}
