package com.tw.hexagon.ddd_sample.application.order.port.in.command;

import jakarta.validation.constraints.NotNull;

import static com.tw.hexagon.ddd_sample.application.Validation.validate;


public record OrderTotalPriceCalculateCommand(@NotNull String id, @NotNull String currency) {

    public OrderTotalPriceCalculateCommand(String id, String currency) {
        this.id = id;
        this.currency = currency;

        validate(this);
    }
}
