package com.grabIt.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.grabIt.domain.Sample;

public class SampleValidator implements Validator {
    
    @Override
    public boolean supports(Class<?> clazz) {
        return Sample.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors e) {
        
        ValidationUtils.rejectIfEmpty(e, "name", "", "name is empty");

        //Sample p = (Sample) target;
        
        //perform additional checks
        //if name already exists or ?
    }
}