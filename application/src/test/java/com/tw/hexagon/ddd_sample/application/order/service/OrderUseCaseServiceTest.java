package com.tw.hexagon.ddd_sample.application.order.service;

import com.tw.hexagon.ddd_sample.application.currency.port.out.CurrencyAmountPort;
import com.tw.hexagon.ddd_sample.application.order.exception.OrderValidationException;
import com.tw.hexagon.ddd_sample.application.order.port.in.command.OrderCreateCommand;
import com.tw.hexagon.ddd_sample.application.order.port.in.command.OrderTotalPriceCalculateCommand;
import com.tw.hexagon.ddd_sample.application.order.port.in.result.OrderCreateResult;
import com.tw.hexagon.ddd_sample.application.order.port.out.OrderPort;
import com.tw.hexagon.ddd_sample.domain.currency.model.CurrencyAmount;
import com.tw.hexagon.ddd_sample.domain.order.model.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderUseCaseServiceTest {

    @Mock
    private OrderPort orderPort;

    @Mock
    private CurrencyAmountPort currencyAmountPort;

    @InjectMocks
    private OrderUseCaseService orderUseCaseService;

    @Captor
    private ArgumentCaptor<Order> orderArgumentCaptor;

    @Test
    void should_throw_exception_when_creating_order_but_quantity_is_null() {
        assertThrows(OrderValidationException.class,
                () -> orderUseCaseService.createOrder(new OrderCreateCommand(null, null)));
    }

    @Test
    void should_create_order_and_return_id() {
        when(orderPort.save(orderArgumentCaptor.capture())).thenReturn("1");

        OrderCreateResult result = orderUseCaseService.createOrder(new OrderCreateCommand(BigDecimal.ONE, BigDecimal.TEN));

        verify(orderPort).save(orderArgumentCaptor.capture());
        Order order = orderArgumentCaptor.getValue();
        assertEquals(BigDecimal.ONE, order.getTotalQuantity());
        assertEquals(BigDecimal.TEN, order.getUnitPrice());
        assertEquals("1", result.id());
    }

    @Test
    void should_calculate_total_price_when_currency_is_usd() {
        when(currencyAmountPort.getExchangeRate(anyString())).thenReturn(new CurrencyAmount("USD", 7.2D));
        when(orderPort.findById(anyString())).thenReturn(java.util.Optional.of(new Order("1", BigDecimal.TEN, BigDecimal.ONE)));

        BigDecimal result = orderUseCaseService.calculateTotalPriceWithCurrency(new OrderTotalPriceCalculateCommand("1", "USD"));

        assertEquals(0, BigDecimal.valueOf(72.0).compareTo(result));
    }
}
