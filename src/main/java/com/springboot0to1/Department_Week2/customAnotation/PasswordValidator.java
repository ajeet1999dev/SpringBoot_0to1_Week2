package com.springboot0to1.Department_Week2.customAnotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordValidation,String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        char ch;
        boolean capitalFlag = false;
        boolean lowerCaseFlag = false;
        boolean specialCharacterFlag = false;
        int strLength = s.length();
        if(strLength <= 10)
            return false;

        for(int i=0;i < s.length();i++) {
            ch = s.charAt(i);
            if (Character.isUpperCase(ch)) {
                capitalFlag = true;
            } else if (Character.isLowerCase(ch)) {
                lowerCaseFlag = true;
            } else if (ch == '.' || ch == '*' || ch == '@' || ch == '&' || ch == '#' || ch == '%' || ch == '$' || ch == '^' || ch =='!' || ch == '~' || ch == '?') {
                specialCharacterFlag=true;
            }
            if(capitalFlag && lowerCaseFlag && specialCharacterFlag)
                return true;
        }
        return false;
    }
}
