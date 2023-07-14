package com.sev4ikwasd.internship_practice.dao.impl;

import com.sev4ikwasd.internship_practice.config.DbConfig;
import com.sev4ikwasd.internship_practice.dao.ServiceDao;
import com.sev4ikwasd.internship_practice.entity.Service;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import static com.sev4ikwasd.internship_practice.utils.JdbcTemplateUtility.queryForObjectToOptional;

@Repository
public class ServiceDaoImpl implements ServiceDao {
    private final JdbcTemplate jdbcTemplate;
    private final DbConfig dbConfig;

    private final RowMapper<Service> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> new Service(resultSet.getInt("object_id"), resultSet.getString("object_vname"), resultSet.getString("object_name"), resultSet.getString("object_sname"), null);

    public ServiceDaoImpl(JdbcTemplate jdbcTemplate, DbConfig dbConfig) {
        this.jdbcTemplate = jdbcTemplate;
        this.dbConfig = dbConfig;
    }

    @Override
    public Optional<Service> read(Integer id) {
        return queryForObjectToOptional(jdbcTemplate, "select object_id, object_vname, object_name, object_sname from icd1.h_dic_objects where class_id = ? and object_id = ?", ROW_MAPPER, dbConfig.getServiceClassId(), id);
    }

    @Override
    public List<Service> readAll() {
        return jdbcTemplate.query("select object_id, object_vname, object_name, object_sname from icd1.h_dic_objects where class_id = ?", ROW_MAPPER, dbConfig.getServiceClassId());
    }
}
