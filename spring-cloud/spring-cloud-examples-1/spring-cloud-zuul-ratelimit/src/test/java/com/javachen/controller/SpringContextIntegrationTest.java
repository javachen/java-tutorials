package com.javachen.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.javachen.ZuulRatelimitDemoApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZuulRatelimitDemoApplication.class)
public class SpringContextIntegrationTest {

    @Test
    public void whenSpringContextIsBootstrapped_thenNoExceptions() {
    }
}