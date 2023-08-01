package com.tw.hexagon.ddd_sample.application;

import com.tw.hexagon.ddd_sample.application.order.exception.OrderValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

import java.util.Set;
import java.util.stream.Collectors;

import static jakarta.validation.Validation.buildDefaultValidatorFactory;

public class Validation {

    private final static Validator validator = buildDefaultValidatorFactory().getValidator();

    public static <T> void validate(T subject) {
        Set<ConstraintViolation<T>> violations = validator.validate(subject);
        if (!violations.isEmpty()) {
            String message = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(":"));
            throw new OrderValidationException(message);
        }
    }
}
