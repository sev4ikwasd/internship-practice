package com.sev4ikwasd.internship_practice.constraint;

import com.sev4ikwasd.internship_practice.validator.CompanyRelationExistsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CompanyRelationExistsValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CompanyRelationExistsConstraint {
    String message() default "company to grouping relation should exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
