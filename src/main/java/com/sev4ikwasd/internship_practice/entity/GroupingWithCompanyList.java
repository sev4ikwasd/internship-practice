package com.sev4ikwasd.internship_practice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class GroupingWithCompanyList {
    private int id;
    private String fullName;
    private String name;
    private String shortName;
    private List<Company> companyList;

    public static GroupingWithCompanyList fromGrouping(Grouping grouping) {
        return new GroupingWithCompanyList(grouping.getId(), grouping.getFullName(), grouping.getName(), grouping.getShortName(), null);
    }
}
