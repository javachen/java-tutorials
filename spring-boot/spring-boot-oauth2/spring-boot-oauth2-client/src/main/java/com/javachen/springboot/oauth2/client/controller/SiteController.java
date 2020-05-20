package com.javachen.springboot.oauth2.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;

@RestController
public class SiteController {

    @Autowired
    private RestOperations restOperations;

    @GetMapping("/")
    @ResponseBody
    public String hello() {
        return "Hello!";
    }

    @GetMapping("/person")
    public String person() {
        String personResourceUrl = "http://localhost:8000/user/person";
        return restOperations.getForObject(personResourceUrl, String.class);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public Object adminAuth() {
        return "Has admin auth!";
    }

}