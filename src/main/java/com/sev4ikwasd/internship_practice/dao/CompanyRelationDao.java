package com.sev4ikwasd.internship_practice.dao;

import com.sev4ikwasd.internship_practice.entity.CompanyRelation;

import java.util.List;
import java.util.Optional;

public interface CompanyRelationDao {
    CompanyRelation create(CompanyRelation instance);

    Optional<CompanyRelation> read(Integer id);

    Optional<Integer> readCompanyId(Integer id);

    List<CompanyRelation> readAll();

    List<CompanyRelation> readAllForGrouping(Integer id);

    CompanyRelation update(CompanyRelation instance);

    void delete(CompanyRelation id);
}
