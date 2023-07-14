package com.sev4ikwasd.internship_practice.dao.impl;

import com.sev4ikwasd.internship_practice.dao.CompanyDao;
import com.sev4ikwasd.internship_practice.entity.Company;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import static com.sev4ikwasd.internship_practice.utils.JdbcTemplateUtility.queryForObjectToOptional;

@Repository
public class CompanyDaoImpl implements CompanyDao {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Company> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> new Company(resultSet.getInt("pred_id"), resultSet.getString("vname"), resultSet.getString("name"), resultSet.getString("sname"), resultSet.getString("sname2"), null);
    private final RowMapper<Integer> STATION_ROW_MAPPER = (ResultSet resultSet, int rowNum) -> resultSet.getInt("stan_m_id");

    public CompanyDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Company create(Company instance) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Optional<Company> read(Integer id) {
        return queryForObjectToOptional(jdbcTemplate, "select pred_id, vname, name, sname, sname2 from icd1.predpriyatia where pred_id = ?", ROW_MAPPER, id);
    }

    @Override
    public Optional<Integer> readStationId(Integer id) {
        return queryForObjectToOptional(jdbcTemplate, "select stan_m_id from icd1.predpriyatia where pred_id = ?", STATION_ROW_MAPPER, id);
    }

    @Override
    public List<Company> readAll() {
        return jdbcTemplate.query("select pred_id, vname, name, sname, sname2 from icd1.predpriyatia", ROW_MAPPER);
    }

    @Override
    public Company update(Company instance) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void delete(Company id) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
