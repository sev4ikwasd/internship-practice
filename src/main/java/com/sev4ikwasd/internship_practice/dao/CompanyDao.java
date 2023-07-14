package com.sev4ikwasd.internship_practice.dao;

import com.sev4ikwasd.internship_practice.entity.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyDao {
    Company create(Company instance);

    Optional<Company> read(Integer id);

    Optional<Integer> readStationId(Integer id);

    List<Company> readAll();

    Company update(Company instance);

    void delete(Company id);
}
