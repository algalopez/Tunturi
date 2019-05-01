package com.ranking.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class SampleDaoJdbcImpl implements SampleDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public SampleDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public SampleDto getSample(Integer id) {

        String query = "SELECT id, sample FROM sample WHERE id = :id";
        SqlParameterSource parameters = new MapSqlParameterSource("id", id);

        return namedParameterJdbcTemplate.queryForObject(query, parameters, new BeanPropertyRowMapper<>(SampleDto.class));
    }
}
