package com.javachen.springboot.oauth2.server.sso;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Order(1)
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    @SneakyThrows
    protected void configure(HttpSecurity http) {
        http.requestMatchers()
                .antMatchers("/login", "/oauth/authorize")
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and().csrf().disable();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        String finalPassword = passwordEncoder().encode("123456");
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.builder().username("admin").password(finalPassword).authorities("ROLE_ADMIN", "ROLE_USER").build());
        manager.createUser(User.builder().username("user").password(finalPassword).authorities("ROLE_USER").build());
        return manager;
    }
}
