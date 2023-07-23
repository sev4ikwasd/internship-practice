package com.sev4ikwasd.internship_practice.repository;

import com.sev4ikwasd.internship_practice.entity.Company;
import com.sev4ikwasd.internship_practice.entity.Grouping;
import com.sev4ikwasd.internship_practice.entity.Service;
import com.sev4ikwasd.internship_practice.entity.ServiceWithGroupingWithCompanyRelationList;
import com.sev4ikwasd.internship_practice.exception.GroupingNotFoundException;

import java.util.List;

public interface ServiceRepository {
    List<Service> getAll();

    List<ServiceWithGroupingWithCompanyRelationList> getAllWithCompanies();

    Grouping getGrouping(Integer id) throws GroupingNotFoundException;

    List<Company> getAllCompaniesForGrouping(Integer id) throws GroupingNotFoundException;
}
