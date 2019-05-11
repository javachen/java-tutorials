package com.javachen.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
@Slf4j
public class GreetingService {
    private String REST_URL_PREFIX = "service-provider";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    public String greeting(String username) {
        return restTemplate.getForObject("http://" + REST_URL_PREFIX + "/greeting/{username}", String.class, username);
    }

    public String greeting2(String username) throws IOException {
        // 选择指定的 service Id
        ServiceInstance serviceInstance = loadBalancerClient.choose(REST_URL_PREFIX);

        return loadBalancerClient.execute(REST_URL_PREFIX, serviceInstance, instance -> {
            //服务器实例，获取 主机名（IP） 和 端口
            String host = instance.getHost();
            int port = instance.getPort();
            String url = "http://" + host + ":" + port + "/greeting/{username}";
            return restTemplate.getForObject(url, String.class, username);
        });
    }
}