package com.javachen.config;

import com.javachen.ping.MyPing;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RibbonConfig {
    @Bean
    public IPing ribbonPing() {
        return new MyPing();
    }

    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
    }
}
