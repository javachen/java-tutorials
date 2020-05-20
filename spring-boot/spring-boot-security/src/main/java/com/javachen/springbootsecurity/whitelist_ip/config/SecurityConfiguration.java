package com.javachen.springbootsecurity.whitelist_ip.config;

import com.javachen.springbootsecurity.whitelist_ip.provider.WhitelistIpAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private WhitelistIpAuthenticationProvider authenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers("/**")
//                .hasIpAddress("192.168.2.111") //允许只有给定IP地址的用户可以访问特定资源
//                .access("isAuthenticated() and hasIpAddress('192.168.2.111')") //允许登陆，再判断访问IP
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic().and()
                .formLogin().permitAll();
    }
}
