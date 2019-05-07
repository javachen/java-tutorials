package com.javachen;

import com.javachen.config.RibbonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@RibbonClient(name = "REST-PROVIDER", configuration = RibbonConfig.class)
public class ConsumerRibbonApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerRibbonApplication.class, args);
    }
}
