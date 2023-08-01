package com.tw.hexagon.ddd_sample.application.order.port.in.command;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

import static com.tw.hexagon.ddd_sample.application.Validation.validate;


public record OrderCreateCommand(@NotNull BigDecimal totalQuantity, @NotNull BigDecimal unitPrice) {

    public OrderCreateCommand(BigDecimal totalQuantity, BigDecimal unitPrice) {
        this.totalQuantity = totalQuantity;
        this.unitPrice = unitPrice;

        validate(this);
    }
}
