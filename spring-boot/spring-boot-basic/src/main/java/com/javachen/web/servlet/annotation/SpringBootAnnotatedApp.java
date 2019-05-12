package com.javachen.web.servlet.annotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * using the following annotations are equivalent:
 * <ul><li>
 * <code>@ServletComponentScan</code>
 * </li><li>
 * <code>@ServletComponentScan(basePackages = "com.javachen.web.servlet.annotation")</code>
 * </li><li>
 * <code>@ServletComponentScan(basePackageClasses = {AttrListener.class, HelloFilter.class, HelloServlet.class})</code>
 * </li></ul>
 */
@SpringBootApplication
@ServletComponentScan("com.javachen.web.servlet.annotation")
public class SpringBootAnnotatedApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootAnnotatedApp.class, args);
    }

}
