package com.sev4ikwasd.internship_practice.validator;

import com.sev4ikwasd.internship_practice.constraint.CompanyExistsConstraint;
import com.sev4ikwasd.internship_practice.exception.CompanyNotFoundException;
import com.sev4ikwasd.internship_practice.repository.CompanyRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CompanyExistsValidator implements ConstraintValidator<CompanyExistsConstraint, Integer> {
    private final CompanyRepository companyRepository;

    public CompanyExistsValidator(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        try {
            companyRepository.getCompany(integer);
            return true;
        } catch (CompanyNotFoundException e) {
            return false;
        }
    }
}
