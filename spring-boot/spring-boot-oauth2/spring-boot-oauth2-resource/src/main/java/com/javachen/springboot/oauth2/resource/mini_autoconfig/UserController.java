package com.javachen.springboot.oauth2.resource.mini_autoconfig;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {
    @GetMapping("/user/me")
    public Principal user(Principal principal) {
        System.out.println(principal);
        return principal;
    }

    @GetMapping("/whoami")
    public String whoami(@AuthenticationPrincipal(expression = "name") String name) {
        return name;
    }
}