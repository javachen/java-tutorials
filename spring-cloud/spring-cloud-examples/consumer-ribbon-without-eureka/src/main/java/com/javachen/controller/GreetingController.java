package com.javachen.controller;

import com.javachen.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
public class GreetingController {
    @Autowired
    private GreetingService greetingService;


    @Autowired
    private LoadBalancerClient loadBalancerClient;

    private String providerServiceName = "producer";


    @RequestMapping("/greeting/{username}")
    public String greeting(@PathVariable("username") String username) {
        return greetingService.greeting(username);
    }

    @RequestMapping("/greeting2/{username}")
    public String index(@PathVariable("username") String username) throws IOException {
        // 选择指定的 service Id
        ServiceInstance serviceInstance = loadBalancerClient.choose(providerServiceName);

        return loadBalancerClient.execute(providerServiceName, serviceInstance, instance -> {
            //服务器实例，获取 主机名（IP） 和 端口
            String host = instance.getHost();
            int port = instance.getPort();
            String url = "http://" + host + ":" + port + "/greeting/{username}";
            RestTemplate restTemplate = new RestTemplate();

            return restTemplate.getForObject(url, String.class, username);
        });

    }
}
