package com.algalopez.ranking.user.data;

import com.algalopez.ranking.user.UserAuthDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UserAuthDaoJdbcImpl implements UserAuthDao {

    private static final String ID_PARAM = "id";
    private static final String USERNAME_PARAM = "username";
    private static final String PW_PARAM = "password";
    private static final String ENABLED_PARAM = "enabled";
    private static final String LOCKED_PARAM = "locked";
    private static final String ROLE_PARAM = "role";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserAuthDaoJdbcImpl(@Autowired NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Long createUserAuth(String username, String password, String role) {
        final String insertSql = "INSERT INTO `user_auth` (username, password, enabled, locked, role) VALUES " +
                "(:" + USERNAME_PARAM + ", :" + PW_PARAM + ", :" + ENABLED_PARAM + ", :" + LOCKED_PARAM + ", :" + ROLE_PARAM + ")";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue(USERNAME_PARAM, username)
                .addValue(PW_PARAM, password)
                .addValue(ENABLED_PARAM, false)
                .addValue(LOCKED_PARAM, false)
                .addValue(ROLE_PARAM, role);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertSql, mapSqlParameterSource, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public void updateUserAuth(UserAuth userAuth) {
        final String updateSql = "UPDATE `user_auth` SET username = :" + USERNAME_PARAM + ", password = :" + PW_PARAM + ", " +
                "enabled = :" + ENABLED_PARAM + ", locked = :" + LOCKED_PARAM + ", role = :" + ROLE_PARAM + " WHERE id = :id;";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue(ID_PARAM, userAuth.getId())
                .addValue(USERNAME_PARAM, userAuth.getUsername())
                .addValue(PW_PARAM, userAuth.getPassword())
                .addValue(ENABLED_PARAM, userAuth.isEnabled())
                .addValue(LOCKED_PARAM, userAuth.isLocked())
                .addValue(ROLE_PARAM, userAuth.getRole());
        namedParameterJdbcTemplate.update(updateSql, mapSqlParameterSource);
    }
}
