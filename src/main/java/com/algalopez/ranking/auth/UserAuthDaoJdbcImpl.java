package com.algalopez.ranking.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Collections;

@Repository
public class UserAuthDaoJdbcImpl implements UserAuthDao {

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
    public UserDetails findByUsername(String username) {

        final String query = "SELECT id, username, password, enabled, locked, role FROM `user_auth` WHERE username = :username;";
        SqlParameterSource parameters = new MapSqlParameterSource(USERNAME_PARAM, username);

        return namedParameterJdbcTemplate.queryForObject(query, parameters, (resultSet, rowNum) ->
                UserAuth.builder()
                        .id(resultSet.getLong("id"))
                        .username(resultSet.getString(USERNAME_PARAM))
                        .password(resultSet.getString(PW_PARAM))
                        .enabled(resultSet.getBoolean(ENABLED_PARAM))
                        .locked(resultSet.getBoolean(LOCKED_PARAM))
                        .authorities(Collections.singletonList(new SimpleGrantedAuthority(resultSet.getString(ROLE_PARAM))))
                        .build()
        );
    }

    @Override
    public Long createUserAuth(String username, String password, UserAuthRole role) {
        final String insertSql = "INSERT INTO `user_auth` (username, password, enabled, locked, role) VALUES " +
                "(:" + USERNAME_PARAM + ", :" + PW_PARAM + ", :" + ENABLED_PARAM + ", :" + LOCKED_PARAM + ", :" + ROLE_PARAM + ")";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue(USERNAME_PARAM, username)
                .addValue(PW_PARAM, password)
                .addValue(ENABLED_PARAM, false)
                .addValue(LOCKED_PARAM, false)
                .addValue(ROLE_PARAM, role.getValue());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertSql, mapSqlParameterSource, keyHolder);
        return keyHolder.getKey().longValue();
    }
}
