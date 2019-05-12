package com.javachen.bean.componentscan.springbootapp;

import com.javachen.bean.componentscan.ExampleBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@ComponentScan(basePackages = "com.javachen.bean.componentscan.animals")
//@ComponentScan ( excludeFilters = @ComponentScan.Filter(type=FilterType.REGEX,pattern="com\\.javachen\\.bean\\.componentscan\\.flowers\\..*"))
//@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = Rose.class))

public class SpringBootComponentScanApp {
    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(SpringBootComponentScanApp.class, args);
        checkBeansPresence("cat", "dog", "rose", "exampleBean", "springBootApp");

        displayAllBeans();
    }

    private static void checkBeansPresence(String... beans) {
        for (String beanName : beans) {
            System.out.println("Is " + beanName + " in ApplicationContext: " + applicationContext.containsBean(beanName));
        }
    }

    public static void displayAllBeans() {
        String[] allBeanNames = applicationContext.getBeanDefinitionNames();
        for (String beanName : allBeanNames) {
            System.out.println(beanName);
        }
    }

    @Bean
    public ExampleBean exampleBean() {
        return new ExampleBean();
    }
}
