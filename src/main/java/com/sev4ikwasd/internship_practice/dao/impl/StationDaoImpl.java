package com.sev4ikwasd.internship_practice.dao.impl;

import com.sev4ikwasd.internship_practice.dao.StationDao;
import com.sev4ikwasd.internship_practice.entity.Station;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Optional;

import static com.sev4ikwasd.internship_practice.utils.JdbcTemplateUtility.queryForObjectToOptional;

@Repository
public class StationDaoImpl implements StationDao {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Station> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> new Station(resultSet.getInt("stan_id"), resultSet.getString("vname"), resultSet.getString("name"));

    public StationDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Station> read(Integer id) {
        return queryForObjectToOptional(jdbcTemplate, "select stan_id, vname, name from icd1.stancii where stan_id = ?", ROW_MAPPER, id);
    }
}
