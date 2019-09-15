package com.algalopez.ranking.user;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    private static final String ID = "id";
    private static final String EMAIL = "email";
    private static final String USERNAME = "username";
    private static final String LEVEL = "level";

    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer levelValue = rs.getObject(LEVEL) == null ? null : Integer.valueOf(rs.getString(LEVEL));
        return User.builder()
                .id(rs.getLong(ID))
                .email(rs.getString(EMAIL))
                .username(rs.getString(USERNAME))
                .level(UserLevel.parseUserLevel(levelValue))
                .build();
    }
}
