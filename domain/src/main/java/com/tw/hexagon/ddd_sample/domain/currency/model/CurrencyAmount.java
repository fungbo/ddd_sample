package com.tw.hexagon.ddd_sample.domain.currency.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class CurrencyAmount {

    private String currency;

    private double rate;

    public BigDecimal calculateCurrencyAmount(BigDecimal amount) {
        return amount.multiply(BigDecimal.valueOf(rate));
    }
}
