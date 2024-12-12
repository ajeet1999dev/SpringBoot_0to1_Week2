package com.springboot0to1.Department_Week2.customAnotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(
        validatedBy = {PrimeNumberValidator.class}
)
public @interface PrimeNumberValidation {

    String message() default "{jakarta.validation.constraints.PrimeNumberOrNot.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
