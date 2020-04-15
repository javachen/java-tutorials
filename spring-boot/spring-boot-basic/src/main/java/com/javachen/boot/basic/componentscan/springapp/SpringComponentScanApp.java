package com.javachen.boot.basic.componentscan.springapp;

import com.javachen.boot.basic.componentscan.ExampleBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
//@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = Rose.class))
//@ComponentScan(basePackages = "com.javachen.boot.basic.componentscan.springapp")
//@ComponentScan(basePackages = "com.javachen.boot.basic.componentscan.animals")
//@ComponentScan (excludeFilters = @ComponentScan.Filter(type=FilterType.REGEX,pattern="com\\.javachen\\.bean\\.componentscan\\.flowers\\..*"))
public class SpringComponentScanApp {

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = new AnnotationConfigApplicationContext(SpringComponentScanApp.class);

        for (String beanName : applicationContext.getBeanDefinitionNames()) {
            System.out.println(beanName);
        }
    }

    @Bean
    public ExampleBean exampleBean() {
        return new ExampleBean();
    }

}