package com.javachen.cloud.oauth2.resource.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {
    @GetMapping("/user/info")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Principal user(Principal principal) {
        return principal;
    }

    @GetMapping("/user/test")
    public String test() {
        return "test";
    }
}
