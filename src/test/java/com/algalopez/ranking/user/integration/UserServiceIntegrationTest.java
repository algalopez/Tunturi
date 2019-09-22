package com.algalopez.ranking.user.integration;

import com.algalopez.ranking.RankingApplication;
import com.algalopez.ranking.user.data.UserInfoDao;
import com.algalopez.ranking.user.UserService;
import com.algalopez.ranking.user.data.UserAuth;
import com.algalopez.ranking.user.data.UserInfo;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RankingApplication.class)
@TestPropertySource(locations = "classpath:test.properties")
@Transactional
public class UserServiceIntegrationTest {

    private static final String ID_PARAM = "id";

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private UserService userService;

    @Test
    public void testCorrectUserCreation() {

        final String email = "email";
        final String password = "password";
        final String role = "role";

        Long id = userService.createUser(email, password, role);

        UserAuth userAuth = findUserAuthById(id);
        assertEquals(password, userAuth.getPassword());
        assertEquals(email, userAuth.getUsername());
        assertEquals(id, userAuth.getId());
        assertEquals(role, userAuth.getRole());
        assertFalse(userAuth.isEnabled());
        assertFalse(userAuth.isLocked());

        UserInfo userInfo = userInfoDao.findUserInfoById(id);
        assertEquals(id, userInfo.getId());
        assertEquals(email, userInfo.getUsername());
        assertEquals(email, userInfo.getEmail());
        assertNull(userInfo.getLevel());
    }

    private UserAuth findUserAuthById(Long id) {
        final String query = "SELECT id, username, password, enabled, locked, role FROM `user_auth` WHERE id = :id;";
        SqlParameterSource parameters = new MapSqlParameterSource(ID_PARAM, id);

        return namedParameterJdbcTemplate.queryForObject(query, parameters, new BeanPropertyRowMapper<>(UserAuth.class));
    }

}
