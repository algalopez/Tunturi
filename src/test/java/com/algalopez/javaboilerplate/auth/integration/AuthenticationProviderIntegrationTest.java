package com.algalopez.javaboilerplate.auth.integration;

import com.algalopez.javaboilerplate.JavaBoilerplateApplication;
import com.algalopez.javaboilerplate.auth.AuthenticationServiceImpl;
import com.algalopez.javaboilerplate.auth.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JavaBoilerplateApplication.class)
@TestPropertySource(locations = "classpath:test.properties")
@Transactional
public class AuthenticationProviderIntegrationTest {

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Test
    public void testCorrectAuthentication() {

        final String username = "user";
        final String credentials = "password";
        User expectedUser = buildUser(username, credentials, true, false, "USER");
        Long userId = insertUser(expectedUser);
        expectedUser.setId(userId);

        Authentication authenticationProvided = new UsernamePasswordAuthenticationToken(expectedUser, credentials);
        Authentication authentication = authenticationService.authenticate(authenticationProvided);
        assertEquals(expectedUser.getUsername(), authentication.getName());
        assertEquals(expectedUser.getPassword(), authentication.getCredentials());
        assertEquals(expectedUser.getAuthorities(), authentication.getAuthorities());
    }

    @Test(expected = BadCredentialsException.class)
    public void testBadCredentials() {

        final String username2 = "user2";
        final String credentials2 = "password2";
        User expectedUser = buildUser(username2, credentials2, true, false, "USER");
        expectedUser.setId(1L);

        Authentication authenticationProvided = new UsernamePasswordAuthenticationToken(expectedUser, credentials2);
        Authentication authentication = authenticationService.authenticate(authenticationProvided);
        assertEquals(expectedUser.getUsername(), authentication.getName());
        assertEquals(expectedUser.getPassword(), authentication.getCredentials());
        assertEquals(expectedUser.getAuthorities(), authentication.getAuthorities());
    }

    @Test(expected = BadCredentialsException.class)
    public void testNonExistingUser() {

        final String username3 = "user3";
        final String credentials3 = "password3";
        User expectedUser = buildUser(username3, credentials3, false, true, "ADMIN");
        Long userId = insertUser(expectedUser);
        expectedUser.setId(userId);

        expectedUser.setUsername("Non existing name");
        Authentication authenticationProvided = new UsernamePasswordAuthenticationToken(expectedUser, credentials3);
        Authentication authentication = authenticationService.authenticate(authenticationProvided);
        assertEquals(expectedUser.getUsername(), authentication.getName());
        assertEquals(expectedUser.getPassword(), authentication.getCredentials());
        assertEquals(expectedUser.getAuthorities(), authentication.getAuthorities());
    }

    private Long insertUser(User user) {

        final String insertSql = "INSERT INTO `user` (username, password, enabled, locked, role) " +
                "VALUES (:username, :password, :enabled, :locked, :role)";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("username", user.getUsername());
        parameters.put("password", user.getPassword());
        parameters.put("enabled", user.isEnabled());
        parameters.put("locked", !user.isAccountNonLocked());
        parameters.put("role", user.getAuthorities().get(0).getAuthority());
        namedParameterJdbcTemplate.update(insertSql, parameters);

        final String queryUser = "SELECT id FROM `user` WHERE username=:username";
        SqlParameterSource queryParams = new MapSqlParameterSource("username", user.getUsername());
        return namedParameterJdbcTemplate.queryForObject(queryUser, queryParams, Long.class);
    }

    private User buildUser(String username, String password, Boolean enabled, Boolean locked, String role) {
        return User.builder()
                .username(username)
                .password(password)
                .enabled(enabled)
                .locked(locked)
                .authorities(Collections.singletonList(new SimpleGrantedAuthority(role)))
                .build();
    }
}
