package com.tw.hexagon.ddd_sample.application.order.service;

import com.tw.hexagon.ddd_sample.application.order.port.in.command.OrderCreateCommand;
import com.tw.hexagon.ddd_sample.application.order.port.in.command.OrderQuantityChangeCommand;
import com.tw.hexagon.ddd_sample.application.order.port.in.command.OrderTotalPriceCalculateCommand;
import com.tw.hexagon.ddd_sample.application.order.exception.OrderNotFoundException;
import com.tw.hexagon.ddd_sample.application.order.port.in.result.OrderCreateResult;
import com.tw.hexagon.ddd_sample.application.order.port.in.result.OrderQueryResult;
import com.tw.hexagon.ddd_sample.application.order.port.in.usecase.OrderUseCasePort;
import com.tw.hexagon.ddd_sample.application.currency.port.out.CurrencyAmountPersistencePort;
import com.tw.hexagon.ddd_sample.application.order.port.out.OrderPersistencePort;
import com.tw.hexagon.ddd_sample.domain.currency.model.CurrencyAmount;
import com.tw.hexagon.ddd_sample.domain.order.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class OrderUseCaseService implements OrderUseCasePort {

    private final OrderPersistencePort orderPort;
    private final CurrencyAmountPersistencePort currencyAmountPort;

    public OrderCreateResult createOrder(OrderCreateCommand command) {
        Order order = new Order();
        order.setTotalQuantity(command.totalQuantity());
        order.setUnitPrice(command.unitPrice());

        return new OrderCreateResult(orderPort.save(order));
    }

    public void updateOrderQuantity(OrderQuantityChangeCommand command) {
        Order order = loadOrder(command.id());
        order.updateQuantity(command.totalQuantity());

        orderPort.save(order);
    }

    public BigDecimal calculateTotalPriceWithCurrency(OrderTotalPriceCalculateCommand command) {
        Order order = loadOrder(command.id());
        BigDecimal amount = order.getTotalPrice();

        CurrencyAmount currencyAmount = currencyAmountPort.getExchangeRate(command.currency());
        return currencyAmount.calculateCurrencyAmount(amount);
    }

    public OrderQueryResult findById(String id) {
        Order order = loadOrder(id);

        return new OrderQueryResult(order.getId(), order.getTotalQuantity(), order.getUnitPrice());
    }

    private Order loadOrder(String id) {
        Optional<Order> order = orderPort.findById(id);
        if (order.isEmpty()) {
            throw new OrderNotFoundException(id);
        }
        return order.get();
    }
}

