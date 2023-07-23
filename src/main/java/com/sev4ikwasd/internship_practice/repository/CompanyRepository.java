package com.sev4ikwasd.internship_practice.repository;

import com.sev4ikwasd.internship_practice.dto.AddCompanyRequest;
import com.sev4ikwasd.internship_practice.dto.UpdateCompanyRequest;
import com.sev4ikwasd.internship_practice.entity.Company;
import com.sev4ikwasd.internship_practice.exception.CompanyNotFoundException;

import java.util.List;

public interface CompanyRepository {
    List<Company> getAllCompanies();

    Company getCompany(Integer id) throws CompanyNotFoundException;

    void addCompany(AddCompanyRequest addCompanyRequest);

    void updateCompany(UpdateCompanyRequest updateCompanyRequest);

    boolean hasGrouping(Integer id, Integer idGrouping);
}
