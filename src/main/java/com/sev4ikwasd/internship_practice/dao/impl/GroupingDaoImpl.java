package com.sev4ikwasd.internship_practice.dao.impl;

import com.sev4ikwasd.internship_practice.config.DbConfig;
import com.sev4ikwasd.internship_practice.dao.GroupingDao;
import com.sev4ikwasd.internship_practice.entity.Grouping;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import static com.sev4ikwasd.internship_practice.utils.JdbcTemplateUtility.queryForObjectToOptional;

@Repository
public class GroupingDaoImpl implements GroupingDao {
    private final JdbcTemplate jdbcTemplate;
    private final DbConfig dbConfig;

    private final String groupingClassIdString;
    private final RowMapper<Grouping> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> new Grouping(resultSet.getInt("object_id"), resultSet.getString("object_vname"), resultSet.getString("object_name"), resultSet.getString("object_sname"));

    public GroupingDaoImpl(JdbcTemplate jdbcTemplate, DbConfig dbConfig) {
        this.jdbcTemplate = jdbcTemplate;
        this.dbConfig = dbConfig;
        groupingClassIdString = String.join(", ", dbConfig.getGroupingClassIdList().stream().map(String::valueOf).toList());
    }

    @Override
    public Optional<Grouping> read(Integer id) {
        return queryForObjectToOptional(jdbcTemplate, "select object_id, object_vname, object_name, object_sname from icd1.h_dic_objects where class_id in (" + groupingClassIdString + ") and object_id = ?", ROW_MAPPER, id);
    }

    @Override
    public List<Grouping> readAllForService(Integer id) {
        return jdbcTemplate.query("select h2.object_id, h2.object_vname, h2.object_name, h2.object_sname from icd3.relations r " +
                "inner join icd1.h_dic_objects h1 on r.id1 = h1.object_id " +
                "inner join icd1.h_dic_objects h2 on r.id2 = h2.object_id " +
                "where h1.class_id = ? and h2.class_id in (" + groupingClassIdString + ") and h1.object_id = ?", ROW_MAPPER, dbConfig.getServiceClassId(), id);
    }
}
