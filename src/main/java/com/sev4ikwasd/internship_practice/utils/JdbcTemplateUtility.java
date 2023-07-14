package com.sev4ikwasd.internship_practice.utils;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Optional;

public class JdbcTemplateUtility {
    public static <T> Optional<T> queryForObjectToOptional(JdbcTemplate jdbcTemplate, String query, RowMapper<T> rowMapper, Object... args) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(query, rowMapper, args));
        } catch (IncorrectResultSizeDataAccessException e) {
            return Optional.empty();
        }
    }
}
