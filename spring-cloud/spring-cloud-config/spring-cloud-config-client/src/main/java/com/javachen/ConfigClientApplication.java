package com.javachen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Set;

@SpringBootApplication
//@EnableScheduling
//@EnableDiscoveryClient
public class ConfigClientApplication {
    private final ContextRefresher contextRefresher;

    @Autowired
    public ConfigClientApplication(ContextRefresher contextRefresher) {
        this.contextRefresher = contextRefresher;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }

    @Scheduled(fixedRate = 3000L)
    public void update() {
        Set<String> keys = contextRefresher.refresh();

        if (!keys.isEmpty()) {
            System.out.println("本次更新的配置项: " + keys);
        }
    }

    @Bean
    public MyHealthIndicator myHealthIndicator() {
        return new MyHealthIndicator();
    }

    private class MyHealthIndicator implements HealthIndicator {
        @Override
        public Health health() {
            Health.Builder builder = Health.status(Status.UP);
            builder.withDetail("name", "MyHealthIndicator");
            builder.withDetail("timestamp", System.currentTimeMillis());
            return builder.build();
        }
    }

}
