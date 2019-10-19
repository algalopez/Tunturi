package com.algalopez.tunturi.auth.integration;

import com.algalopez.tunturi.TunturiApplication;
import com.algalopez.tunturi.auth.Auth;
import com.algalopez.tunturi.auth.AuthDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TunturiApplication.class)
@TestPropertySource(locations = "classpath:test.properties")
@Transactional
public class AuthDaoIntegrationTest {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private AuthDao authDao;

    @Test
    public void testFindByUsername() {

        Long userId;

        Auth expectedAuth1 = buildUser("user1", "pass1", false, false, "USER");
        userId = insertUser(expectedAuth1);
        expectedAuth1.setId(userId);

        Auth expectedAuth2 = buildUser("user2", "pass2", true, true, "ADMIN");
        userId = insertUser(expectedAuth2);
        expectedAuth2.setId(userId);

        Auth actualAuth1 = (Auth) authDao.findUserByUsername(expectedAuth1.getUsername());
        Auth actualAuth2 = (Auth) authDao.findUserByUsername(expectedAuth2.getUsername());
        assertEquals(expectedAuth1, actualAuth1);
        assertEquals(expectedAuth2, actualAuth2);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testFindNonExistingUserByUsername() {

        authDao.findUserByUsername("non existing user");
    }

    private Long insertUser(Auth auth) {

        final String insertSql = "INSERT INTO `user_auth` (username, password, enabled, locked, role) " +
                "VALUES (:username, :password, :enabled, :locked, :role)";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("username", auth.getUsername());
        parameters.put("password", auth.getPassword());
        parameters.put("enabled", auth.isEnabled());
        parameters.put("locked", !auth.isAccountNonLocked());
        parameters.put("role", auth.getAuthorities().iterator().next().getAuthority());
//        parameters.put("role", auth.getAuthorities().get(0).getAuthority());
        namedParameterJdbcTemplate.update(insertSql, parameters);

        final String queryUser = "SELECT id FROM `user_auth` WHERE username=:username";
        SqlParameterSource queryParams = new MapSqlParameterSource("username", auth.getUsername());
        return namedParameterJdbcTemplate.queryForObject(queryUser, queryParams, Long.class);
    }

    private Auth buildUser(String username, String password, Boolean enabled, Boolean locked, String role) {
        return new Auth(null, username, password, enabled, locked, Collections.singletonList(new SimpleGrantedAuthority(role)));
//        return Auth.builder()
//                .username(username)
//                .password(password)
//                .enabled(enabled)
//                .locked(locked)
//                .authorities(Collections.singletonList(new SimpleGrantedAuthority(role)))
//                .build();
    }
}
