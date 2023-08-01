package com.tw.hexagon.ddd_sample.application.order.port.in.command;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

import static com.tw.hexagon.ddd_sample.application.Validation.validate;


public record OrderQuantityChangeCommand(@NotNull String id, @NotNull BigDecimal totalQuantity) {

    public OrderQuantityChangeCommand(String id, BigDecimal totalQuantity) {
        this.id = id;
        this.totalQuantity = totalQuantity;

        validate(this);
    }
}
