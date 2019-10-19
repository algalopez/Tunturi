package com.algalopez.tunturi.user.integration.data;

import com.algalopez.tunturi.TunturiApplication;
import com.algalopez.tunturi.user.data.UserInfo;
import com.algalopez.tunturi.user.data.UserInfoDao;
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
@SpringBootTest(classes = TunturiApplication.class)
@TestPropertySource(locations = "classpath:test.properties")
@Transactional
public class UserInfoDaoIntegrationTest {

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Test
    public void testFindUserById() {
        final Long id = 1L;
        UserInfo expectedUserInfo = buildUser(id, "email", "username", 1);
        insertUser(expectedUserInfo);

        assertEquals(expectedUserInfo, userInfoDao.findUserInfoById(id));
    }

    @Test
    public void testFindUserWithNullLevel() {
        final Long id = 1L;
        UserInfo expectedUserInfo = buildUser(id, "email", "email", null);
        insertUser(expectedUserInfo);

        assertEquals(expectedUserInfo, userInfoDao.findUserInfoById(id));
    }

    @Test
    public void testUserCreation() {
        final Long id = 1L;
        final String email = "email";
        UserInfo expectedUserInfo = buildUser(id, email, email, null);
        userInfoDao.createUserInfo(expectedUserInfo);
        assertEquals(expectedUserInfo, getUserList().get(0));
    }

    @Test
    public void testUpdateUser() {
        UserInfo initialUserInfo = buildUser(2L, "email1", "username1", 0);
        UserInfo finalUserInfo = buildUser(2L, "email2", "username2", 1);

        userInfoDao.createUserInfo(initialUserInfo);
        userInfoDao.updateUserInfo(finalUserInfo);

        assertEquals(initialUserInfo.getId(), getUserList().get(0).getId());
        assertEquals(finalUserInfo.getUsername(), getUserList().get(0).getUsername());
        assertEquals(finalUserInfo.getEmail(), getUserList().get(0).getEmail());
        assertEquals(finalUserInfo.getLevel(), getUserList().get(0).getLevel());

    }

    private UserInfo buildUser(Long id, String email, String username, Integer level) {
        return UserInfo.builder()
                .id(id)
                .email(email)
                .username(username)
                .level(level)
                .build();
    }

    private List<UserInfo> getUserList() {
        final String queryUser = "SELECT id, email, username, level FROM `user` ORDER BY id ASC";
        return namedParameterJdbcTemplate.query(queryUser, new MapSqlParameterSource(), new BeanPropertyRowMapper<>(UserInfo.class));
    }

    private void insertUser(UserInfo userInfo) {
        final String insertUser = "INSERT INTO `user` (id, email, username, level) VALUES (:id, :email, :username, :level)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", userInfo.getId())
                .addValue("email", userInfo.getEmail())
                .addValue("username", userInfo.getUsername())
                .addValue("level", userInfo.getLevel());
        namedParameterJdbcTemplate.update(insertUser, mapSqlParameterSource);
    }
}
