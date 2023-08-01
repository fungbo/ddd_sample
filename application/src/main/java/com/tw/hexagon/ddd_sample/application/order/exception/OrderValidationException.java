package com.tw.hexagon.ddd_sample.application.order.exception;

public class OrderValidationException extends RuntimeException {

    public OrderValidationException(String message) {
        super(message);
    }
}
