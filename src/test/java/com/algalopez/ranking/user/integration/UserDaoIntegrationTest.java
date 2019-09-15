package com.algalopez.ranking.user.integration;

import com.algalopez.ranking.RankingApplication;
import com.algalopez.ranking.user.User;
import com.algalopez.ranking.user.UserDao;
import com.algalopez.ranking.user.UserLevel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RankingApplication.class)
@TestPropertySource(locations = "classpath:test.properties")
@Transactional
public class UserDaoIntegrationTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Test
    public void testFindUserById() {
        final Long id = 1L;
        User expectedUser = buildUser(id, "email", "username", UserLevel.BEGINNER);
        insertUser(expectedUser);

        assertEquals(expectedUser, userDao.findUserById(id));
    }

    @Test
    public void testFindUserWithNullLevel() {
        final Long id = 1L;
        User expectedUser = buildUser(id, "email", "email", null);
        insertUser(expectedUser);

        assertEquals(expectedUser, userDao.findUserById(id));
    }

    @Test
    public void testUserCreation() {
        final Long id = 1L;
        final String email = "email";
        userDao.createUser(id, email);
        User expectedUser = buildUser(id, email, email, null);
        assertEquals(expectedUser, getUserList().get(0));
    }

    @Test
    public void testUpdateUser() {
        userDao.createUser(2L, "email2");
        User expectedUser = buildUser(2L, "email1", "username", UserLevel.BEGINNER);
        userDao.updateUser(expectedUser);

        assertEquals(expectedUser, getUserList().get(0));
    }

    private User buildUser(Long id, String email, String username, UserLevel level) {
        return User.builder()
                .id(id)
                .email(email)
                .username(username)
                .level(level)
                .build();
    }

    private List<User> getUserList() {
        final String queryUser = "SELECT id, email, username, level FROM `user` ORDER BY id ASC";
        return namedParameterJdbcTemplate.query(queryUser, new MapSqlParameterSource(), new BeanPropertyRowMapper<>(User.class));
    }

    private void insertUser(User user) {
        Integer levelValue = user.getLevel() == null ? null : user.getLevel().getLevelValue();
        final String insertUser = "INSERT INTO `user` (id, email, username, level) VALUES (:id, :email, :username, :level)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("email", user.getEmail())
                .addValue("username", user.getUsername())
                .addValue("level", levelValue);
        namedParameterJdbcTemplate.update(insertUser, mapSqlParameterSource);
    }
}
