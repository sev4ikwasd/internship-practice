package com.sev4ikwasd.internship_practice.constraint;

import com.sev4ikwasd.internship_practice.validator.CompanyExistsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CompanyExistsValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CompanyExistsConstraint {
    String message() default "company should exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
