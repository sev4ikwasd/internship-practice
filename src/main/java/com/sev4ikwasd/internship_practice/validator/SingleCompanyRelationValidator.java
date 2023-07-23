package com.sev4ikwasd.internship_practice.validator;

import com.sev4ikwasd.internship_practice.constraint.SingleCompanyRelationConstraint;
import com.sev4ikwasd.internship_practice.dto.AddCompanyRequest;
import com.sev4ikwasd.internship_practice.repository.CompanyRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SingleCompanyRelationValidator implements ConstraintValidator<SingleCompanyRelationConstraint, AddCompanyRequest> {
    private final CompanyRepository companyRepository;

    public SingleCompanyRelationValidator(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public boolean isValid(AddCompanyRequest addCompanyRequest, ConstraintValidatorContext constraintValidatorContext) {
        return !companyRepository.hasGrouping(addCompanyRequest.id(), addCompanyRequest.idGrouping());
    }
}
