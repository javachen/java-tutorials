package com.javachen;

import com.javachen.config.RibbonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@RibbonClient(name = "REST-PROVIDER", configuration = RibbonConfig.class)
public class ConsumerFeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerFeignApplication.class, args);
    }
}
