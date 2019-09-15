package com.algalopez.ranking.auth.integration;

import com.algalopez.ranking.RankingApplication;
import com.algalopez.ranking.auth.AuthenticationServiceImpl;
import com.algalopez.ranking.auth.UserAuth;
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
@SpringBootTest(classes = RankingApplication.class)
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
        UserAuth expectedUserAuth = buildUser(username, credentials, true, false, "USER");
        Long userId = insertUser(expectedUserAuth);
        expectedUserAuth.setId(userId);

        Authentication authenticationProvided = new UsernamePasswordAuthenticationToken(expectedUserAuth, credentials);
        Authentication authentication = authenticationService.authenticate(authenticationProvided);
        assertEquals(expectedUserAuth.getUsername(), authentication.getName());
        assertEquals(expectedUserAuth.getPassword(), authentication.getCredentials());
        assertEquals(expectedUserAuth.getAuthorities(), authentication.getAuthorities());
    }

    @Test(expected = BadCredentialsException.class)
    public void testBadCredentials() {

        final String username2 = "user2";
        final String credentials2 = "password2";
        UserAuth expectedUserAuth = buildUser(username2, credentials2, true, false, "USER");
        expectedUserAuth.setId(1L);

        Authentication authenticationProvided = new UsernamePasswordAuthenticationToken(expectedUserAuth, credentials2);
        Authentication authentication = authenticationService.authenticate(authenticationProvided);
        assertEquals(expectedUserAuth.getUsername(), authentication.getName());
        assertEquals(expectedUserAuth.getPassword(), authentication.getCredentials());
        assertEquals(expectedUserAuth.getAuthorities(), authentication.getAuthorities());
    }

    @Test(expected = BadCredentialsException.class)
    public void testNonExistingUser() {

        final String username3 = "user3";
        final String credentials3 = "password3";
        UserAuth expectedUserAuth = buildUser(username3, credentials3, false, true, "ADMIN");
        Long userId = insertUser(expectedUserAuth);
        expectedUserAuth.setId(userId);

        expectedUserAuth.setUsername("Non existing name");
        Authentication authenticationProvided = new UsernamePasswordAuthenticationToken(expectedUserAuth, credentials3);
        Authentication authentication = authenticationService.authenticate(authenticationProvided);
        assertEquals(expectedUserAuth.getUsername(), authentication.getName());
        assertEquals(expectedUserAuth.getPassword(), authentication.getCredentials());
        assertEquals(expectedUserAuth.getAuthorities(), authentication.getAuthorities());
    }

    private Long insertUser(UserAuth userAuth) {

        final String insertSql = "INSERT INTO `user_auth` (username, password, enabled, locked, role) " +
                "VALUES (:username, :password, :enabled, :locked, :role)";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("username", userAuth.getUsername());
        parameters.put("password", userAuth.getPassword());
        parameters.put("enabled", userAuth.isEnabled());
        parameters.put("locked", !userAuth.isAccountNonLocked());
        parameters.put("role", userAuth.getAuthorities().get(0).getAuthority());
        namedParameterJdbcTemplate.update(insertSql, parameters);

        final String queryUser = "SELECT id FROM `user_auth` WHERE username=:username";
        SqlParameterSource queryParams = new MapSqlParameterSource("username", userAuth.getUsername());
        return namedParameterJdbcTemplate.queryForObject(queryUser, queryParams, Long.class);
    }

    private UserAuth buildUser(String username, String password, Boolean enabled, Boolean locked, String role) {
        return UserAuth.builder()
                .username(username)
                .password(password)
                .enabled(enabled)
                .locked(locked)
                .authorities(Collections.singletonList(new SimpleGrantedAuthority(role)))
                .build();
    }
}
