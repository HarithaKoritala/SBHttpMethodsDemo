package com.hari.sb.httpdemo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ProductTypeValidator.class)
public @interface ValidateProductType {
    public String message() default "Invalid Product Type: It shoudl be either mobile or clothing";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default{};
}
