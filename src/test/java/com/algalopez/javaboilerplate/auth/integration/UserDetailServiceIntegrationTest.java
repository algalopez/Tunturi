package com.algalopez.javaboilerplate.auth.integration;

import com.algalopez.javaboilerplate.JavaBoilerplateApplication;
import com.algalopez.javaboilerplate.auth.User;
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
@SpringBootTest(classes = JavaBoilerplateApplication.class)
@TestPropertySource(locations = "classpath:test.properties")
@Transactional
public class UserDetailServiceIntegrationTest {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Test
    public void testLoadUserByUsername(){

        Long userId;

        User expectedUser1 = buildUser("user1", "pass1", false, false, "USER");
        userId = insertUser(expectedUser1);
        expectedUser1.setId(userId);

        User expectedUser2 = buildUser("user2", "pass2", true, true, "ADMIN");
        userId = insertUser(expectedUser2);
        expectedUser2.setId(userId);

        User actualUser1 = (User) userDetailsService.loadUserByUsername(expectedUser1.getUsername());
        User actualUser2 = (User) userDetailsService.loadUserByUsername(expectedUser2.getUsername());

        assertEquals(expectedUser1 ,actualUser1);
        assertEquals(expectedUser2 ,actualUser2);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testNonExistingUser() {
        userDetailsService.loadUserByUsername("NonExistingUser");
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
