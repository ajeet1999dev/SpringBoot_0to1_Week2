package com.springboot0to1.Department_Week2.customAnotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PrimeNumberValidator implements ConstraintValidator<PrimeNumberValidation,Integer> {

    @Override
    public boolean isValid(Integer primeNumber, ConstraintValidatorContext constraintValidatorContext) {

        if(primeNumber<=1)
            return false;
        for(int i =2; i<primeNumber; i++){
            if(primeNumber % 2 == 0)
                return false;
        }
        return true;
    }
}
