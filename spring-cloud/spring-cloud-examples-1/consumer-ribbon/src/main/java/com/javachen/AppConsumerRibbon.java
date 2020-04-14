package com.javachen;

import com.javachen.config.RibbonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;

@SpringBootApplication
@RibbonClients(
        //设置一个 ribbon 客户端名称
        @RibbonClient(name = "service-provider", configuration = RibbonConfig.class)
)
public class AppConsumerRibbon {
    public static void main(String[] args) {
        SpringApplication.run(AppConsumerRibbon.class, args);
    }
}
