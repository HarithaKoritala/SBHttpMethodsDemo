package com.hari.sb.httpdemo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class ProductTypeValidator implements ConstraintValidator<ValidateProductType, String> {
    @Override
    public void initialize(ValidateProductType constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String productType, ConstraintValidatorContext constraintValidatorContext) {
        List<String> productTypes = Arrays.asList("mobile","clothing");
        return productTypes.contains(productType);
    }
}
