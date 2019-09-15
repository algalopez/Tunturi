package com.algalopez.ranking.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationProvider {

    private static final String INVALID_USERNAME_OR_CREDENTIALS = "Invalid username or password";
    private static final String LOCKED_ACCOUNT = "This account has been locked. If this is an error, please contact the admin";
    private static final String DISABLED_ACCOUNT = "This account has been disabled. If this is an error, please contact the admin";

    private UserServiceImpl userService;

    public AuthenticationServiceImpl(@Autowired UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(final Authentication authentication) {

        final String providedUsername = authentication.getName();
        final Object providedPassword = authentication.getCredentials();

        if (providedUsername == null || providedPassword == null) {
            return null;
        }

        if (providedUsername.isEmpty() || providedPassword.toString().isEmpty()) {
            return null;
        }

        UserAuth userAuth;
        try {
            userAuth = (UserAuth) this.userService.loadUserByUsername(providedUsername);
        } catch (UsernameNotFoundException e) {
            throw new BadCredentialsException(INVALID_USERNAME_OR_CREDENTIALS);
        }

        if (!providedPassword.equals(userAuth.getPassword())) {
            throw new BadCredentialsException(INVALID_USERNAME_OR_CREDENTIALS);
        }

        if (!userAuth.isEnabled()) {
            throw new DisabledException(DISABLED_ACCOUNT);
        }

        if (userAuth.isLocked()) {
            throw new LockedException(LOCKED_ACCOUNT);
        }

        return new UsernamePasswordAuthenticationToken(
                userAuth.getUsername(),
                userAuth.getPassword(),
                userAuth.getAuthorities());
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
