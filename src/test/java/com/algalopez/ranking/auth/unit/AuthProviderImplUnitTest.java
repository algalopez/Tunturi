package com.algalopez.ranking.auth.unit;

import com.algalopez.ranking.auth.Auth;
import com.algalopez.ranking.auth.AuthProviderImpl;
import com.algalopez.ranking.auth.AuthServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthProviderImplUnitTest {

    @Mock
    private AuthServiceImpl userService;

    private AuthProviderImpl authenticationService;

    @Before
    public void prepareMocks() {
        authenticationService = new AuthProviderImpl(userService);
    }

    @Test
    public void authenticateNullName() {

        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn(null);
        when(authentication.getCredentials()).thenReturn("pass");

        Authentication actualAuthentication = authenticationService.authenticate(authentication);
        assertNull(actualAuthentication);
    }

    @Test
    public void testNullCredentials() {

        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("user");
        when(authentication.getCredentials()).thenReturn(null);

        Authentication actualAuthentication = authenticationService.authenticate(authentication);
        assertNull(actualAuthentication);
    }

    @Test
    public void testEmptyName() {

        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("");
        when(authentication.getCredentials()).thenReturn("pass");

        Authentication actualAuthentication = authenticationService.authenticate(authentication);
        assertNull(actualAuthentication);
    }

    @Test
    public void testEmptyCredentials() {

        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("user");
        when(authentication.getCredentials()).thenReturn("");

        Authentication actualAuthentication = authenticationService.authenticate(authentication);
        assertNull(actualAuthentication);
    }

    @Test(expected = BadCredentialsException.class)
    public void testNonExistingUser() {

        final String username = "user";
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn(username);
        when(authentication.getCredentials()).thenReturn("pass");
        when(userService.loadUserByUsername(username)).thenThrow(new UsernameNotFoundException("Error"));

        authenticationService.authenticate(authentication);
    }

    @Test(expected = BadCredentialsException.class)
    public void testInvalidCredentials() {

        final String username = "user";
        final String password = "pass";
        Auth auth = buildUser(1L, username, password + "_diff", true, false, "USER");

        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn(username);
        when(authentication.getCredentials()).thenReturn(password);
        when(userService.loadUserByUsername(username)).thenReturn(auth);

        authenticationService.authenticate(authentication);

    }

    @Test(expected = DisabledException.class)
    public void testDisabledUser() {

        final String username = "user";
        final String password = "pass";
        Auth auth = buildUser(2L, username, password, false, false, "USER");

        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn(username);
        when(authentication.getCredentials()).thenReturn(password);
        when(userService.loadUserByUsername(username)).thenReturn(auth);

        authenticationService.authenticate(authentication);
    }

    @Test(expected = LockedException.class)
    public void testLockedUser() {

        final String username = "user";
        final String password = "pass";
        Auth auth = buildUser(3L, username, password, true, true, "USER");

        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn(username);
        when(authentication.getCredentials()).thenReturn(password);
        when(userService.loadUserByUsername(username)).thenReturn(auth);

        authenticationService.authenticate(authentication);
    }

    @Test
    public void testCorrectUser() {

        final String username = "admin";
        final String password = "admin";
        Auth auth = buildUser(4L, username, password, true, false, "ADMIN");

        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn(username);
        when(authentication.getCredentials()).thenReturn(password);
        when(userService.loadUserByUsername(username)).thenReturn(auth);

        Authentication actualAuthentication = authenticationService.authenticate(authentication);
        assertEquals(username, actualAuthentication.getName());
        assertEquals(password, actualAuthentication.getCredentials());
        assertEquals(auth.getAuthorities(), actualAuthentication.getAuthorities());
    }

    @Test
    public void testCorrectSupport() {
        final String username = "admin";
        final String password = "admin";
        Auth auth = buildUser(4L, username, password, true, false, "ADMIN");

        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn(username);
        when(authentication.getCredentials()).thenReturn(password);
        when(userService.loadUserByUsername(username)).thenReturn(auth);

        Authentication actualAuthentication = authenticationService.authenticate(authentication);
        assertTrue(authenticationService.supports(actualAuthentication.getClass()));

    }

    @Test
    public void testInvalidSupport() {

        assertFalse(authenticationService.supports(AnonymousAuthenticationToken.class));
    }

    private Auth buildUser(Long id, String username, String password, Boolean enabled, Boolean locked, String role) {
        return Auth.builder()
                .id(id)
                .username(username)
                .password(password)
                .enabled(enabled)
                .locked(locked)
                .authorities(Collections.singletonList(new SimpleGrantedAuthority(role)))
                .build();
    }
}
