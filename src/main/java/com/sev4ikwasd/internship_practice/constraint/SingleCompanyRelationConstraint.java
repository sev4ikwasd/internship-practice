package com.sev4ikwasd.internship_practice.constraint;

import com.sev4ikwasd.internship_practice.validator.SingleCompanyRelationValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = SingleCompanyRelationValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SingleCompanyRelationConstraint {
    String message() default "company should be related to only one company";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
