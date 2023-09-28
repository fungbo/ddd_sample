package com.tw.hexagon.ddd_sample.domain.order.exception;

import lombok.Getter;

@Getter
public class CurrencyNotFoundException extends RuntimeException {

    private final String code = "1000";

    public CurrencyNotFoundException(String currency) {
        super("Currency " + currency + " not found");
    }
}
