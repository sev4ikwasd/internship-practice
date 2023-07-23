package com.sev4ikwasd.internship_practice.dao.impl;

import com.sev4ikwasd.internship_practice.config.DbConfig;
import com.sev4ikwasd.internship_practice.dao.CompanyRelationDao;
import com.sev4ikwasd.internship_practice.entity.CompanyRelation;
import com.sev4ikwasd.internship_practice.entity.Grouping;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.sev4ikwasd.internship_practice.utils.JdbcTemplateUtility.queryForObjectToOptional;

@Repository
public class CompanyRelationDaoImpl implements CompanyRelationDao {
    private final JdbcTemplate jdbcTemplate;
    private final DbConfig dbConfig;

    private final String companyClassIdString;
    private final RowMapper<CompanyRelation> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> new CompanyRelation(resultSet.getInt("object_id"), resultSet.getString("object_vname"), resultSet.getString("object_name"), resultSet.getString("object_sname"), null);
    private final RowMapper<Integer> COMPANY_ROW_MAPPER = (ResultSet resultSet, int rowNum) -> resultSet.getInt("refer");

    public CompanyRelationDaoImpl(JdbcTemplate jdbcTemplate, DbConfig dbConfig) {
        this.jdbcTemplate = jdbcTemplate;
        this.dbConfig = dbConfig;
        companyClassIdString = String.join(", ", dbConfig.getCompanyClassIdList().stream().map(String::valueOf).toList());
    }

    @Override
    @Transactional
    public CompanyRelation create(CompanyRelation companyRelation, Grouping grouping) {
        //TODO correct class_id and rel_id
        var newHDicObjectsId = jdbcTemplate.query("select (max(h_dic_objects_id) + 1) as new_id from icd1.h_dic_objects", (ResultSet resultSet, int rowNum) -> resultSet.getInt("new_id")).get(0);
        var newObjectId = jdbcTemplate.query("select (max(object_id) + 1) as new_id from icd1.h_dic_objects", (ResultSet resultSet, int rowNum) -> resultSet.getInt("new_id")).get(0);
        jdbcTemplate.update("insert into icd1.h_dic_objects(h_dic_objects_id, object_id, class_id, object_vname, object_name, object_sname, refer, cor_tip, date_nd, cor_time) " +
                        "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", newHDicObjectsId, newObjectId, dbConfig.getCompanyClassIdList().get(1), companyRelation.getFullName(),
                companyRelation.getName(), companyRelation.getShortName(), companyRelation.getCompany().getId(), "I", LocalDate.now(), LocalDateTime.now());
        jdbcTemplate.update("insert into icd3.relations(id_rel, id1, id2, cor_tip, date_nd, cor_time) values(?, ?, ?, ?, ?, ?)",
                120, grouping.getId(), newObjectId, "I", LocalDate.now(), LocalDateTime.now());
        companyRelation.setId(newObjectId);
        return companyRelation;
    }

    @Override
    public Optional<CompanyRelation> read(Integer id) {
        return queryForObjectToOptional(jdbcTemplate, "select object_id, object_vname, object_name, object_sname from icd1.h_dic_objects where class_id in (" + companyClassIdString + ") and object_id = ?", ROW_MAPPER, id);
    }

    @Override
    public Optional<Integer> readCompanyId(Integer id) {
        return queryForObjectToOptional(jdbcTemplate, "select refer from icd1.h_dic_objects where class_id in (" + companyClassIdString + ") and object_id = ?", COMPANY_ROW_MAPPER, id);
    }

    @Override
    public List<CompanyRelation> readAllForGrouping(Integer id) {
        return jdbcTemplate.query("select h2.object_id, h2.object_vname, h2.object_name, h2.object_sname from icd3.relations r " +
                "inner join icd1.h_dic_objects h1 on r.id1 = h1.object_id " +
                "inner join icd1.h_dic_objects h2 on r.id2 = h2.object_id " +
                "where h1.class_id = ? and h2.class_id in (" + companyClassIdString + ") and h1.object_id = ?", ROW_MAPPER, dbConfig.getGroupingClassId(), id);
    }

    @Override
    public CompanyRelation update(CompanyRelation instance) {
        jdbcTemplate.update("update icd1.h_dic_objects set object_sname = ?, cor_time = ? where object_id = ?", instance.getShortName(), LocalDateTime.now(), instance.getId());
        return read(instance.getId()).get();
    }
}
