package com.sev4ikwasd.internship_practice.dto;

import com.sev4ikwasd.internship_practice.constraint.CompanyExistsConstraint;
import com.sev4ikwasd.internship_practice.constraint.GroupingExistsConstraint;
import com.sev4ikwasd.internship_practice.constraint.SingleCompanyRelationConstraint;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@SingleCompanyRelationConstraint
public record AddCompanyRequest(@CompanyExistsConstraint Integer id, @GroupingExistsConstraint Integer idGrouping,
                                @Min(1) @Max(2) int shortNameChoice) {
}
