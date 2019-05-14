package com.ranking.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private static final String CLIENT_ID = "client";
    private static final String CLIENT_SECRET = "{noop}secret";
    private static final String GRANT_PASSWORD = "password";
    private static final String GRANT_REFRESH_TOKEN = "refresh_token";
    private static final String SCOPE_READ = "read";
    private static final String SCOPE_WRITE = "write";
    private static final String SCOPE_TRUST = "trust";

    private static final int ACCESS_TOKEN_VALIDITY_SECONDS = 60 * 60;
    private static final int REFRESH_TOKEN_VALIDITY_SECONDS = 6 * 60 * 60;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        // configures the clients (applications connecting to the server)
        clients.inMemory()
                .withClient(CLIENT_ID)
                .secret(CLIENT_SECRET)
                .authorizedGrantTypes(GRANT_PASSWORD, GRANT_REFRESH_TOKEN)
                .scopes(SCOPE_READ, SCOPE_WRITE, SCOPE_TRUST)
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
                .refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);
//                .resourceIds("resource");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager);
    }


//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer configurer) throws Exception {
//
//        // hook up the users into the auth server (these come from our database - or from our in-memory mock data we created earlier)
//        configurer.tokenStore(tokenStore());
//        configurer.authenticationManager(authenticationManager);
//        configurer.userDetailsService(userDetailsService);
//    }

}