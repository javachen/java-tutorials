package com.javachen.springboot.oauth2.server.sso;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * 授权配置：
 * 1、配置客户端
 * 2、token存储位置
 * 3、谁能访问token
 */
@AllArgsConstructor
@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    //    private TokenStore tokenStore;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;

    /**
     * token存储位置
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //如果授权类型是password，则必须配置认证管理器
        endpoints
//                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }

//    @Bean
//    public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory) {
//        return new RedisTokenStore(redisConnectionFactory);
//    }


    /**
     * 谁能访问token
     *
     * @param oauthServer
     * @throws Exception
     */
    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    /**
     * 配置客户端：implicit、password、refresh_token、authorization_code
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        String finalSecret = passwordEncoder.encode("secret");
        clients.inMemory()
                .withClient("web")
                .secret(finalSecret)
                .redirectUris("http://localhost:8000/login", "http://localhost:8080/login")
                .authorities("ROLE_ADMIN", "ROLE_USER")
                .accessTokenValiditySeconds(3600)//配置访问token的有效期
                .refreshTokenValiditySeconds(36000)//配置刷新token的有效期
                .authorizedGrantTypes("password", "refresh_token", "authorization_code", "implicit")
                .autoApprove(false)
                .scopes("read", "write", "trust")
        ;
    }
}
