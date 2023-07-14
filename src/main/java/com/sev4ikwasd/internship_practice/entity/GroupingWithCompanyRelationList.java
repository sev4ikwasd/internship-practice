package com.sev4ikwasd.internship_practice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class GroupingWithCompanyRelationList extends Grouping {
    private List<CompanyRelation> companyRelationList;
}
