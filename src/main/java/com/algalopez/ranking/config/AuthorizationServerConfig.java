package com.algalopez.ranking.config;


import com.algalopez.ranking.auth.UserServiceImpl;
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
    private static final String CLIENT_SECRET = "{bcrypt}$2a$10$.xFW4HsW2Bat0oDR9g4dAOIZlS5N.2BngXLsqZKS67/si6rcNIO3W";
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

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.inMemory()
                .withClient(CLIENT_ID)
                .secret(CLIENT_SECRET)
                .authorizedGrantTypes(GRANT_PASSWORD, GRANT_REFRESH_TOKEN)
                .scopes(SCOPE_READ, SCOPE_WRITE, SCOPE_TRUST)
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
                .refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(tokenStore);
        endpoints.authenticationManager(authenticationManager);
    }
}
