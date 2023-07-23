package com.sev4ikwasd.internship_practice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ServiceWithGroupingWithCompanyRelationList {
    private int id;
    private String fullName;
    private String name;
    private String shortName;
    private List<GroupingWithCompanyList> groupingList;

    public static ServiceWithGroupingWithCompanyRelationList fromService(Service service) {
        return new ServiceWithGroupingWithCompanyRelationList(service.getId(), service.getFullName(), service.getName(), service.getShortName(), service.getGroupingList().stream().map(GroupingWithCompanyList::fromGrouping).toList());
    }
}
