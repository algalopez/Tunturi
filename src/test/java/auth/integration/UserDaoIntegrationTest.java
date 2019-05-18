package auth.integration;

import com.ranking.RankingApplication;
import com.ranking.auth.User;
import com.ranking.auth.UserDao;
import lombok.extern.slf4j.Slf4j;
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
@SpringBootTest(classes = RankingApplication.class)
@TestPropertySource(locations = "classpath:test.properties")
@Transactional
@Slf4j
public class UserDaoIntegrationTest {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private UserDao userDao;

    @Test
    public void testFindByUsername() {

        Long userId;

        User expectedUser1 = buildUser("user1", "pass1", false, false, "USER");
        userId = insertUser(expectedUser1);
        expectedUser1.setId(userId);

        User expectedUser2 = buildUser("user2", "pass2", true, true, "ADMIN");
        userId = insertUser(expectedUser2);
        expectedUser2.setId(userId);

        User actualUser1 = (User)userDao.findByUsername(expectedUser1.getUsername());
        User actualUser2 = (User)userDao.findByUsername(expectedUser2.getUsername());
        assertEquals(expectedUser1, actualUser1);
        assertEquals(expectedUser2, actualUser2);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testFindNonExistingUserByUsername() {
        userDao.findByUsername("non existing user");
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
