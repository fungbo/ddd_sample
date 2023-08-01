package com.tw.hexagon.ddd_sample.domain.order.exception;

public class OrderAmountException extends RuntimeException {

    public OrderAmountException(String message) {
        super(message);
    }
}
