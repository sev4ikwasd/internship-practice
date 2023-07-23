package com.sev4ikwasd.internship_practice.dao;

import com.sev4ikwasd.internship_practice.entity.CompanyRelation;
import com.sev4ikwasd.internship_practice.entity.Grouping;

import java.util.List;
import java.util.Optional;

public interface CompanyRelationDao {
    CompanyRelation create(CompanyRelation instance, Grouping grouping);

    Optional<CompanyRelation> read(Integer id);

    Optional<Integer> readCompanyId(Integer id);

    List<CompanyRelation> readAllForGrouping(Integer id);

    CompanyRelation update(CompanyRelation instance);
}
