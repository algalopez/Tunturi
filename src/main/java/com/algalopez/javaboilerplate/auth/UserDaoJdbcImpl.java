package com.algalopez.javaboilerplate.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Collections;

@Repository
public class UserDaoJdbcImpl implements UserDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserDaoJdbcImpl(@Autowired NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public UserDetails findByUsername(String username) {

        final String query = "SELECT id, username, password, enabled, locked, role FROM ranking.`user` WHERE user.username = :username;";
        SqlParameterSource parameters = new MapSqlParameterSource("username", username);

        return namedParameterJdbcTemplate.queryForObject(query, parameters, (resultSet, rowNum) ->
                User.builder()
                        .id(resultSet.getLong("id"))
                        .username(resultSet.getString("username"))
                        .password(resultSet.getString("password"))
                        .enabled(resultSet.getBoolean("enabled"))
                        .locked(resultSet.getBoolean("locked"))
                        .authorities(Collections.singletonList(new SimpleGrantedAuthority(resultSet.getString("role"))))
                        .build()
        );
    }
}
