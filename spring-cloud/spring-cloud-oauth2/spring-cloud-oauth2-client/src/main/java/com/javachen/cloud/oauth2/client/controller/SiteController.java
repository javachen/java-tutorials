package com.javachen.cloud.oauth2.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SiteController {

    @Autowired
    private RestOperations restOperations;

    @GetMapping("/")
    @ResponseBody
    public String hello() {
        return "Hello!";
    }

    @GetMapping("/personInfo")
    public String person() {
        String personResourceUrl = "http://localhost:9998/person";
        return restOperations.getForObject(personResourceUrl, String.class);
    }

    @GetMapping("/getCurrentUser")
    public Object getCurrentUser(Authentication authentication) {
        return authentication;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public Object adminAuth() {
        return "Has admin auth!";
    }

}