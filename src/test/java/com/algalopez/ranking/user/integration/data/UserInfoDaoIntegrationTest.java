package com.algalopez.ranking.user.integration.data;

import com.algalopez.ranking.RankingApplication;
import com.algalopez.ranking.user.data.UserInfoDao;
import com.algalopez.ranking.user.data.UserInfo;
import com.algalopez.ranking.user.data.UserInfoLevel;
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
public class UserInfoDaoIntegrationTest {

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Test
    public void testFindUserById() {
        final Long id = 1L;
        UserInfo expectedUserInfo = buildUser(id, "email", "username", UserInfoLevel.BEGINNER);
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
        userInfoDao.createUserInfo(id, email);
        UserInfo expectedUserInfo = buildUser(id, email, email, null);
        assertEquals(expectedUserInfo, getUserList().get(0));
    }

    @Test
    public void testUpdateUser() {
        userInfoDao.createUserInfo(2L, "email2");
        UserInfo expectedUserInfo = buildUser(2L, "email1", "username", UserInfoLevel.BEGINNER);
        userInfoDao.updateUserInfo(expectedUserInfo);

        assertEquals(expectedUserInfo, getUserList().get(0));
    }

    private UserInfo buildUser(Long id, String email, String username, UserInfoLevel level) {
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
        Integer levelValue = userInfo.getLevel() == null ? null : userInfo.getLevel().getLevelValue();
        final String insertUser = "INSERT INTO `user` (id, email, username, level) VALUES (:id, :email, :username, :level)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", userInfo.getId())
                .addValue("email", userInfo.getEmail())
                .addValue("username", userInfo.getUsername())
                .addValue("level", levelValue);
        namedParameterJdbcTemplate.update(insertUser, mapSqlParameterSource);
    }
}
