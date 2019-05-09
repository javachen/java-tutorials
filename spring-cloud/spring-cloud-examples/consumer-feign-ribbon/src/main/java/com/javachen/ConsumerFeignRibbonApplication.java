package com.javachen;

import com.javachen.config.RibbonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@RibbonClient(name = "PROVIDER", configuration = RibbonConfig.class)
public class ConsumerFeignRibbonApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerFeignRibbonApplication.class, args);
    }
}
