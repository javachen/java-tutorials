package com.javachen.controller;

import com.javachen.service.GreetingService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeoutException;

@RestController
@Slf4j
public class GreetingController {
    @Autowired
    private GreetingService greetingService;

    private final static Random random = new Random();

    @HystrixCommand(fallbackMethod = "defaultGreeting")
    @RequestMapping("/greeting/{username}")
    public String getGreeting(@PathVariable("username") String username) throws TimeoutException {
        long executeTime = random.nextInt(200);

        log.info("executeTime: {}", executeTime);

        if (executeTime > 100) { // 执行时间超过了 100 ms
            throw new TimeoutException("Execution is timeout!");
        }
        return greetingService.greeting(username);
    }

    @HystrixCommand(
            commandProperties = { // Command 配置
                    // 设置操作时间为 100 毫秒
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")
            },
            fallbackMethod = "defaultGreeting" // 设置 fallback 方法
    )
    @RequestMapping("/greeting2/{username}")
    public String getGreeting2(@PathVariable("username") String username) throws TimeoutException, InterruptedException {
        long executeTime = random.nextInt(200);

        Thread.sleep(executeTime);

        log.info("sleep time: {}", executeTime);

        return greetingService.greeting2(username);
    }

    private String defaultGreeting(String username, Throwable throwable) {
        log.info("fallback method");
        return "Hello User!";
    }
}
