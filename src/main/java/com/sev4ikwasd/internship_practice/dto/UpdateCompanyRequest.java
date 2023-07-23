package com.sev4ikwasd.internship_practice.dto;

import com.sev4ikwasd.internship_practice.constraint.CompanyExistsConstraint;
import com.sev4ikwasd.internship_practice.constraint.CompanyRelationExistsConstraint;
import com.sev4ikwasd.internship_practice.constraint.GroupingExistsConstraint;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@CompanyRelationExistsConstraint
public record UpdateCompanyRequest(@CompanyExistsConstraint Integer id, @GroupingExistsConstraint Integer idGrouping,
                                   @Min(1) @Max(2) int shortNameChoice) {
}
