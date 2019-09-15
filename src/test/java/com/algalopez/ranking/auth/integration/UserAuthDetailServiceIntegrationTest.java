package com.algalopez.ranking.auth.integration;

import com.algalopez.ranking.RankingApplication;
import com.algalopez.ranking.auth.UserAuth;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
public class UserAuthDetailServiceIntegrationTest {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Test
    public void testLoadUserByUsername(){

        Long userId;

        UserAuth expectedUserAuth1 = buildUser("user1", "pass1", false, false, "USER");
        userId = insertUser(expectedUserAuth1);
        expectedUserAuth1.setId(userId);

        UserAuth expectedUserAuth2 = buildUser("user2", "pass2", true, true, "ADMIN");
        userId = insertUser(expectedUserAuth2);
        expectedUserAuth2.setId(userId);

        UserAuth actualUserAuth1 = (UserAuth) userDetailsService.loadUserByUsername(expectedUserAuth1.getUsername());
        UserAuth actualUserAuth2 = (UserAuth) userDetailsService.loadUserByUsername(expectedUserAuth2.getUsername());

        assertEquals(expectedUserAuth1, actualUserAuth1);
        assertEquals(expectedUserAuth2, actualUserAuth2);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testNonExistingUser() {
        userDetailsService.loadUserByUsername("NonExistingUser");
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
