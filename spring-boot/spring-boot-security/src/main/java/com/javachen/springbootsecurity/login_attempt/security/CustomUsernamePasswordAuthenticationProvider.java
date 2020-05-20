package com.javachen.springbootsecurity.login_attempt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomUsernamePasswordAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private LoginAttemptService loginAttemptService;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        WebAuthenticationDetails webAuthenticationDetails = (WebAuthenticationDetails) auth.getDetails();

        if (loginAttemptService.isBlocked(webAuthenticationDetails.getRemoteAddress())) {
            throw new BadCredentialsException("Invalid ip");
        }
        String username = auth.getName();
        String password = auth.getCredentials().toString();

        String usernameDB = "user";
        String passwordDb = "$2y$12$4uxqZ3Ji/kFT5yGNAfIPwuAKERt.s0qVWGJBAxyEhg7Vc0Z8ijt5q"; //password

        boolean passwordMatch = BCrypt.checkpw(password, passwordDb);

        if (username.equals(usernameDB) && passwordMatch) {
            return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
        } else {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}