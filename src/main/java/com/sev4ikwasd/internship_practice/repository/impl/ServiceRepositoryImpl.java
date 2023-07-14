package com.sev4ikwasd.internship_practice.repository.impl;

import com.sev4ikwasd.internship_practice.dao.GroupingDao;
import com.sev4ikwasd.internship_practice.dao.ServiceDao;
import com.sev4ikwasd.internship_practice.entity.Grouping;
import com.sev4ikwasd.internship_practice.entity.Service;
import com.sev4ikwasd.internship_practice.repository.ServiceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ServiceRepositoryImpl implements ServiceRepository {
    private final ServiceDao serviceDao;
    private final GroupingDao groupingDao;

    public ServiceRepositoryImpl(ServiceDao serviceDao, GroupingDao groupingDao) {
        this.serviceDao = serviceDao;
        this.groupingDao = groupingDao;
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
}
