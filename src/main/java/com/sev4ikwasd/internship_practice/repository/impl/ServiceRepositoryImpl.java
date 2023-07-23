package com.sev4ikwasd.internship_practice.repository.impl;

import com.sev4ikwasd.internship_practice.dao.*;
import com.sev4ikwasd.internship_practice.entity.*;
import com.sev4ikwasd.internship_practice.exception.GroupingNotFoundException;
import com.sev4ikwasd.internship_practice.repository.ServiceRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ServiceRepositoryImpl implements ServiceRepository {
    private final ServiceDao serviceDao;
    private final GroupingDao groupingDao;
    private final CompanyRelationDao companyRelationDao;
    private final CompanyDao companyDao;
    private final StationDao stationDao;

    public ServiceRepositoryImpl(ServiceDao serviceDao, GroupingDao groupingDao, CompanyRelationDao companyRelationDao, CompanyDao companyDao, StationDao stationDao) {
        this.serviceDao = serviceDao;
        this.groupingDao = groupingDao;
        this.companyRelationDao = companyRelationDao;
        this.companyDao = companyDao;
        this.stationDao = stationDao;
    }


    @Override
    public List<Service> getAll() {
        List<Service> serviceList = serviceDao.readAll();
        for (var service : serviceList) {
            List<Grouping> groupingList = groupingDao.readAllForService(service.getId());
            service.setGroupingList(groupingList);
        }

        return serviceList;
    }

    @Override
    public List<ServiceWithGroupingWithCompanyRelationList> getAllWithCompanies() {
        List<Service> serviceList = serviceDao.readAll();
        for (var service : serviceList) {
            List<Grouping> groupingList = groupingDao.readAllForService(service.getId());
            service.setGroupingList(groupingList);
        }

        List<ServiceWithGroupingWithCompanyRelationList> serviceWithCompanyList = serviceList.stream().map(ServiceWithGroupingWithCompanyRelationList::fromService).toList();
        for (var service : serviceWithCompanyList) {
            for (var grouping : service.getGroupingList()) {
                List<CompanyRelation> companyRelationList = companyRelationDao.readAllForGrouping(grouping.getId());
                List<Company> companyList = new ArrayList<>();
                for (CompanyRelation companyRelation : companyRelationList) {
                    companyRelationDao.readCompanyId(companyRelation.getId()).flatMap(companyDao::read).ifPresent(companyList::add);
                }
                for (var company : companyList) {
                    companyDao.readStationId(company.getId()).flatMap(stationDao::read).ifPresent(company::setStation);
                }
                grouping.setCompanyList(companyList);
            }
        }

        return serviceWithCompanyList;
    }

    @Override
    public Grouping getGrouping(Integer id) throws GroupingNotFoundException {
        var groupingOptional = groupingDao.read(id);
        if (groupingOptional.isEmpty()) {
            throw new GroupingNotFoundException(id);
        }
        return groupingOptional.get();
    }

    @Override
    public List<Company> getAllCompaniesForGrouping(Integer id) throws GroupingNotFoundException {
        var groupingOptional = groupingDao.read(id);
        if (groupingOptional.isEmpty()) {
            throw new GroupingNotFoundException(id);
        }
        List<CompanyRelation> companyRelationList = companyRelationDao.readAllForGrouping(id);
        List<Company> companyList = new ArrayList<>();
        for (CompanyRelation companyRelation : companyRelationList) {
            companyRelationDao.readCompanyId(companyRelation.getId()).flatMap(companyDao::read).ifPresent(companyList::add);
        }
        for (var company : companyList) {
            companyDao.readStationId(company.getId()).flatMap(stationDao::read).ifPresent(company::setStation);
        }
        return companyList;
    }
}
