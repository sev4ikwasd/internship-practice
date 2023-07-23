package com.sev4ikwasd.internship_practice.repository.impl;

import com.sev4ikwasd.internship_practice.dao.CompanyDao;
import com.sev4ikwasd.internship_practice.dao.CompanyRelationDao;
import com.sev4ikwasd.internship_practice.dao.GroupingDao;
import com.sev4ikwasd.internship_practice.dao.StationDao;
import com.sev4ikwasd.internship_practice.dto.AddCompanyRequest;
import com.sev4ikwasd.internship_practice.dto.UpdateCompanyRequest;
import com.sev4ikwasd.internship_practice.entity.Company;
import com.sev4ikwasd.internship_practice.entity.CompanyRelation;
import com.sev4ikwasd.internship_practice.exception.CompanyNotFoundException;
import com.sev4ikwasd.internship_practice.exception.GroupingNotFoundException;
import com.sev4ikwasd.internship_practice.repository.CompanyRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompanyRepositoryImpl implements CompanyRepository {
    private final GroupingDao groupingDao;
    private final CompanyDao companyDao;
    private final StationDao stationDao;
    private final CompanyRelationDao companyRelationDao;

    public CompanyRepositoryImpl(GroupingDao groupingDao, CompanyDao companyDao, StationDao stationDao, CompanyRelationDao companyRelationDao) {
        this.groupingDao = groupingDao;
        this.companyDao = companyDao;
        this.stationDao = stationDao;
        this.companyRelationDao = companyRelationDao;
    }

    @Override
    public List<Company> getAllCompanies() {
        List<Company> companyList = companyDao.readAll();
        for (var company : companyList) {
            companyDao.readStationId(company.getId()).flatMap(stationDao::read).ifPresent(company::setStation);
        }
        return companyList;
    }

    @Override
    public Company getCompany(Integer id) throws CompanyNotFoundException {
        var companyOptional = companyDao.read(id);
        if (companyOptional.isEmpty())
            throw new CompanyNotFoundException(id);
        var company = companyOptional.get();
        companyDao.readStationId(id).flatMap(stationDao::read).ifPresent(company::setStation);
        return company;
    }

    @Override
    public void addCompany(AddCompanyRequest companyRequest) {
        var company = companyDao.read(companyRequest.id()).orElseThrow(() -> new CompanyNotFoundException(companyRequest.id()));
        var grouping = groupingDao.read(companyRequest.idGrouping()).orElseThrow(() -> new GroupingNotFoundException(companyRequest.idGrouping()));
        var companyRelation = new CompanyRelation(company.getFullName(), company.getName(),
                companyRequest.shortNameChoice() == 1 ? company.getShortName() : company.getShortName2(), company);
        companyRelationDao.create(companyRelation, grouping);
    }

    @Override
    public void updateCompany(UpdateCompanyRequest companyRequest) {
        var company = companyDao.read(companyRequest.id()).orElseThrow(() -> new CompanyNotFoundException(companyRequest.id()));
        var grouping = groupingDao.read(companyRequest.idGrouping()).orElseThrow(() -> new GroupingNotFoundException(companyRequest.idGrouping()));
        Integer id = null;
        for (var companyRelation : companyRelationDao.readAllForGrouping(grouping.getId())) {
            var companyId = companyRelationDao.readCompanyId(companyRelation.getId());
            if (companyId.isPresent() && companyId.get().equals(company.getId()))
                id = companyRelation.getId();

        }
        if (id == null) throw new CompanyNotFoundException(companyRequest.id());
        var companyRelation = new CompanyRelation(id, company.getFullName(), company.getName(),
                companyRequest.shortNameChoice() == 1 ? company.getShortName() : company.getShortName2(), company);
        companyRelationDao.update(companyRelation);
    }

    @Override
    public boolean hasGrouping(Integer id, Integer idGrouping) {
        List<Optional<Integer>> companyIdOptionals = companyRelationDao.readAllForGrouping(idGrouping).stream().map(CompanyRelation::getId).map(companyRelationDao::readCompanyId).toList();
        for (var companyIdOptional : companyIdOptionals) {
            if (companyIdOptional.isPresent() && companyIdOptional.get().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
