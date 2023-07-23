package com.sev4ikwasd.internship_practice.validator;

import com.sev4ikwasd.internship_practice.constraint.CompanyRelationExistsConstraint;
import com.sev4ikwasd.internship_practice.dto.UpdateCompanyRequest;
import com.sev4ikwasd.internship_practice.repository.CompanyRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CompanyRelationExistsValidator implements ConstraintValidator<CompanyRelationExistsConstraint, UpdateCompanyRequest> {
    private final CompanyRepository companyRepository;

    public CompanyRelationExistsValidator(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public boolean isValid(UpdateCompanyRequest updateCompanyRequest, ConstraintValidatorContext constraintValidatorContext) {
        return companyRepository.hasGrouping(updateCompanyRequest.id(), updateCompanyRequest.idGrouping());
    }
}
