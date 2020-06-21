package com.algalopez.tunturi.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Collections;

@Repository
public class AuthDaoJdbcImpl implements AuthDao {

    private static final String ID_PARAM = "id";
    private static final String USERNAME_PARAM = "username";
    private static final String PW_PARAM = "password";
    private static final String ENABLED_PARAM = "enabled";
    private static final String LOCKED_PARAM = "locked";
    private static final String ROLE_PARAM = "role";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public AuthDaoJdbcImpl(@Autowired NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public UserDetails findUserByUsername(String username) {

        final String query = "SELECT id, username, password, enabled, locked, role FROM `user_auth` WHERE username = :username;";
        SqlParameterSource parameters = new MapSqlParameterSource(USERNAME_PARAM, username);

        return namedParameterJdbcTemplate.queryForObject(query, parameters, (resultSet, rowNum) ->
                Auth.builder()
                        .id(resultSet.getLong(ID_PARAM))
                        .username(resultSet.getString(USERNAME_PARAM))
                        .password(resultSet.getString(PW_PARAM))
                        .enabled(resultSet.getBoolean(ENABLED_PARAM))
                        .locked(resultSet.getBoolean(LOCKED_PARAM))
                        .authorities(Collections.singletonList(new SimpleGrantedAuthority(resultSet.getString(ROLE_PARAM))))
                        .build()
        );
    }
}
