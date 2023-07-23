package com.sev4ikwasd.internship_practice.validator;

import com.sev4ikwasd.internship_practice.constraint.GroupingExistsConstraint;
import com.sev4ikwasd.internship_practice.exception.GroupingNotFoundException;
import com.sev4ikwasd.internship_practice.repository.ServiceRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GroupingExistsValidator implements ConstraintValidator<GroupingExistsConstraint, Integer> {
    private final ServiceRepository serviceRepository;

    public GroupingExistsValidator(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        try {
            serviceRepository.getGrouping(integer);
            return true;
        } catch (GroupingNotFoundException e) {
            return false;
        }
    }
}
