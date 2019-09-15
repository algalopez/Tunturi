package com.algalopez.ranking.user;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoJdbcImpl implements UserDao {

    private static final String ID_PARAM = "id";
    private static final String USERNAME_PARAM = "username";
    private static final String EMAIL_PARAM = "email";
    private static final String LEVEL_PARAM = "level";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public User findUserById(Long id) {
        final String selectSql = "SELECT * FROM `user` WHERE id = :" + ID_PARAM + "";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource(ID_PARAM, id);
        return namedParameterJdbcTemplate.queryForObject(selectSql, mapSqlParameterSource, new UserRowMapper());
    }

    @Override
    public Long createUser(Long id, String email) {
        final String insertSql = "INSERT INTO `user` (id, email, username, level) VALUES " +
                "(:" + ID_PARAM + ", :" + EMAIL_PARAM + ", :" + USERNAME_PARAM + ", :" + LEVEL_PARAM + ")";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue(ID_PARAM, id)
                .addValue(EMAIL_PARAM, email)
                .addValue(USERNAME_PARAM, email)
                .addValue(LEVEL_PARAM, null);
        namedParameterJdbcTemplate.update(insertSql, mapSqlParameterSource);
        return id;
    }

    @Override
    public void updateUser(User user) {
        final String updateSql = "UPDATE `user` " +
                "SET username = :" + USERNAME_PARAM + ", email = :" + EMAIL_PARAM + ", level = :" + LEVEL_PARAM + " " +
                "WHERE id = :" + ID_PARAM + ";";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue(ID_PARAM, user.getId())
                .addValue(EMAIL_PARAM, user.getEmail())
                .addValue(USERNAME_PARAM, user.getUsername())
                .addValue(LEVEL_PARAM, user.getLevel().getLevelValue());
        namedParameterJdbcTemplate.update(updateSql, mapSqlParameterSource);
    }
}
