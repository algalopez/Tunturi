package com.algalopez.ranking.user.integration.data;

import com.algalopez.ranking.RankingApplication;
import com.algalopez.ranking.user.data.UserAuthDao;
import com.algalopez.ranking.user.data.UserAuth;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RankingApplication.class)
@TestPropertySource(locations = "classpath:test.properties")
@Transactional
public class UserAuthDaoIntegrationTest {

    private static final String ROLE_USER = "USER";
    private static final String ROLE_ADMIN = "ADMIN";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private UserAuthDao userAuthDao;

    @Test
    public void testFindUserById() {
        UserAuth expectedUserAuth = buildUser("user1", "pass1", false, false, ROLE_USER);
        Long id = insertUser(expectedUserAuth);
        expectedUserAuth.setId(id);

        assertEquals(expectedUserAuth, userAuthDao.findUserAuthById(id));
    }

    @Test
    public void testUserAuthCreation() {

        UserAuth expectedUserAuth1 = buildUser("username1", "password1", false, false, ROLE_USER);
        Long id1 = userAuthDao.createUserAuth(expectedUserAuth1);
        expectedUserAuth1.setId(id1);

        UserAuth expectedUserAuth2 = buildUser("username2", "password2", false, false, ROLE_ADMIN);
        Long id2 = userAuthDao.createUserAuth(expectedUserAuth2);
        expectedUserAuth2.setId(id2);

        List<UserAuth> authList = getUserAuthList();
        assertEquals(expectedUserAuth1, authList.get(0));
        assertEquals(expectedUserAuth2, authList.get(1));
    }

    @Test
    public void testUpdateUserAuth() {

        UserAuth initialAuth = buildUser("user1", "pass1", false, false, ROLE_USER);
        Long id = insertUser(initialAuth);
        initialAuth.setId(id);
        UserAuth expectedAuth = buildUser("user2", "pass2", true, true, ROLE_ADMIN);
        expectedAuth.setId(id);

        userAuthDao.updateUserAuth(expectedAuth);
        List<UserAuth> authList = getUserAuthList();
        assertEquals(expectedAuth, authList.get(0));
    }

    private Long insertUser(UserAuth userAuth) {

        final String insertSql = "INSERT INTO `user_auth` (username, password, enabled, locked, role) " +
                "VALUES (:username, :password, :enabled, :locked, :role)";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("username", userAuth.getUsername());
        parameters.put("password", userAuth.getPassword());
        parameters.put("enabled", userAuth.isEnabled());
        parameters.put("locked", userAuth.isLocked());
        parameters.put("role", userAuth.getRole());
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
                .role(role)
                .build();
    }

    private List<UserAuth> getUserAuthList() {

        final String queryUser = "SELECT id, username, password, enabled, locked, role FROM `user_auth` ORDER BY id ASC";
        return namedParameterJdbcTemplate.query(queryUser, new MapSqlParameterSource(), new BeanPropertyRowMapper<>(UserAuth.class));
    }
}
