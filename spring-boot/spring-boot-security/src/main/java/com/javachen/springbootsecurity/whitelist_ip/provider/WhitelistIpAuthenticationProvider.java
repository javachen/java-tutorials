package com.javachen.springbootsecurity.whitelist_ip.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class WhitelistIpAuthenticationProvider implements AuthenticationProvider {

    Set<String> whitelist = new HashSet<String>();

    public WhitelistIpAuthenticationProvider() {
        whitelist.add("192.168.2.111");
    }

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        WebAuthenticationDetails details = (WebAuthenticationDetails) auth.getDetails();
        String userIp = details.getRemoteAddress();
        //TODO 可以修改为正则匹配
        if (!whitelist.contains(userIp)) {
            throw new BadCredentialsException("Invalid IP Address");
        }
        return auth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}