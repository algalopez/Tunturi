package com.algalopez.ranking.user.integration;

import com.algalopez.ranking.RankingApplication;
import com.algalopez.ranking.user.UserService;
import com.algalopez.ranking.user.data.UserInfoDao;
import com.algalopez.ranking.user.model.User;
import com.algalopez.ranking.user.model.UserAuth;
import com.algalopez.ranking.user.model.UserInfo;
import com.algalopez.ranking.user.model.UserRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RankingApplication.class)
@TestPropertySource(locations = "classpath:test.properties")
@Transactional
public class UserServiceIntegrationTest {

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private UserService userService;

    @Test
    public void testValidUserCreation() {

        User user = buildUser("user1", "email", "pass1", 1, true, false, UserRole.USER);
        Long userId = userService.createUser(user);

        Map<String, Object> retrievedUser = findUserById(userId);
        assertEquals(user.getUserInfo().getUsername(), retrievedUser.get("username"));
        assertEquals(user.getUserInfo().getEmail(), retrievedUser.get("email"));
        assertEquals(user.getUserAuth().getPassword(), retrievedUser.get("password"));
        assertEquals(user.getUserInfo().getLevel(), retrievedUser.get("level"));
        assertEquals(user.getUserAuth().isEnabled(), retrievedUser.get("enabled"));
        assertEquals(user.getUserAuth().isLocked(), retrievedUser.get("locked"));
        assertEquals(user.getUserAuth().getRole().getRoleValue(), retrievedUser.get("role"));
    }

    @Test
    public void testFindExistingUserById() {

        User expectedUser = buildUser("user1", "email", "pass1", 1, true, false, UserRole.USER);
        Long userId = userService.createUser(expectedUser);
        expectedUser.getUserAuth().setId(userId);
        expectedUser.getUserInfo().setId(userId);
        User actualUser = userService.findUserById(userId);

        assertNotEquals(expectedUser.getUserAuth().getPassword(), actualUser.getUserAuth().getPassword());
        expectedUser.getUserAuth().setPassword(actualUser.getUserAuth().getPassword());
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testValidUserUpdate() {

        User initialUser = buildUser("user1", "email1", "pass1", 1, true, false, UserRole.USER);
        Long userId = userService.createUser(initialUser);

        User finalUser = buildUser("user2", "email2", "pass2", 8, false, true, UserRole.ADMIN);
        finalUser.getUserAuth().setId(userId);
        finalUser.getUserInfo().setId(userId);

        userService.updateUser(finalUser);
        Map<String, Object> retrievedUser = findUserById(userId);
        assertEquals(finalUser.getUserInfo().getUsername(), retrievedUser.get("username"));
        assertEquals(finalUser.getUserInfo().getEmail(), retrievedUser.get("email"));
        assertEquals(finalUser.getUserAuth().getPassword(), retrievedUser.get("password"));
        assertEquals(finalUser.getUserInfo().getLevel(), retrievedUser.get("level"));
        assertEquals(finalUser.getUserAuth().isEnabled(), retrievedUser.get("enabled"));
        assertEquals(finalUser.getUserAuth().isLocked(), retrievedUser.get("locked"));
        assertEquals(finalUser.getUserAuth().getRole().getRoleValue(), retrievedUser.get("role"));
    }

    @Test
    public void testValidUserInfoUpdate() {

        User initialUser = buildUser("user1", "email1", "pass1", 1, true, false, UserRole.USER);
        Long userId = userService.createUser(initialUser);


        User finalUser = buildUser("user2", "email2", "pass2", 8, false, true, UserRole.ADMIN);
        finalUser.getUserAuth().setId(userId);
        finalUser.getUserInfo().setId(userId);

        userService.updateUserInfo(finalUser);

        Map<String, Object> retrievedUser = findUserById(userId);
        assertEquals(initialUser.getUserInfo().getUsername(), retrievedUser.get("username"));
        assertEquals(initialUser.getUserAuth().getPassword(), retrievedUser.get("password"));
        assertEquals(initialUser.getUserAuth().isEnabled(), retrievedUser.get("enabled"));
        assertEquals(initialUser.getUserAuth().isLocked(), retrievedUser.get("locked"));
        assertEquals(initialUser.getUserAuth().getRole().getRoleValue(), retrievedUser.get("role"));
        assertEquals(finalUser.getUserInfo().getEmail(), retrievedUser.get("email"));
        assertEquals(finalUser.getUserInfo().getLevel(), retrievedUser.get("level"));
    }

    @Test
    public void testUserPasswordPatch() {
        String oldPassword = "pass1";
        String newPassword = "pass2";
        User initialUser = buildUser("user1", "email1", oldPassword, 2, true, false, UserRole.USER);
        Long userId = userService.createUser(initialUser);

        userService.patchUserPassword(userId, newPassword);
        Map<String, Object> retrievedUser = findUserById(userId);
        assertEquals(newPassword, retrievedUser.get("password"));
    }

    private Map<String, Object> findUserById(Long id) {
        final String query = "SELECT auth.id as id, auth.username as username, auth.password, auth.enabled, auth.locked, auth.role, info.email, info.`level` " +
                "FROM `user_auth` auth INNER JOIN `user` info on auth.id = info.id and auth.username = info.username WHERE info.id = :id;";
        SqlParameterSource parameters = new MapSqlParameterSource("id", id);

        return namedParameterJdbcTemplate.queryForMap(query, parameters);
    }

    private User buildUser(String username, String email, String password, Integer level, Boolean enabled, Boolean locked, UserRole role) {

        UserInfo userInfo = UserInfo.builder()
                .email(email)
                .username(username)
                .level(level)
                .build();

        UserAuth userAuth = UserAuth.builder()
                .username(username)
                .password(password)
                .enabled(enabled)
                .locked(locked)
                .role(role)
                .build();

        return User.builder().userInfo(userInfo).userAuth(userAuth).build();
    }

}
