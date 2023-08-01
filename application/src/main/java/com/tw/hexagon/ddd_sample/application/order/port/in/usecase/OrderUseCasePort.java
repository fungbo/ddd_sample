package com.tw.hexagon.ddd_sample.application.order.port.in.usecase;

import com.tw.hexagon.ddd_sample.application.order.port.in.command.OrderCreateCommand;
import com.tw.hexagon.ddd_sample.application.order.port.in.command.OrderQuantityChangeCommand;
import com.tw.hexagon.ddd_sample.application.order.port.in.command.OrderTotalPriceCalculateCommand;
import com.tw.hexagon.ddd_sample.application.order.port.in.result.OrderCreateResult;
import com.tw.hexagon.ddd_sample.application.order.port.in.result.OrderQueryResult;

import java.math.BigDecimal;

public interface OrderUseCasePort {

    OrderCreateResult createOrder(OrderCreateCommand command);

    void updateOrderQuantity(OrderQuantityChangeCommand command);

    BigDecimal calculateTotalPriceWithCurrency(OrderTotalPriceCalculateCommand command);

    OrderQueryResult findById(String id);
}
