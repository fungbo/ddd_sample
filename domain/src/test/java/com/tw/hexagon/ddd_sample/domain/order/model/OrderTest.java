package com.tw.hexagon.ddd_sample.domain.order.model;

import com.tw.hexagon.ddd_sample.domain.order.exception.OrderAmountException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void should_throw_exception_when_quantity_less_than_current_one() {
        Order order = new Order("1", BigDecimal.valueOf(10), BigDecimal.valueOf(10));

        assertThrows(OrderAmountException.class, () -> order.updateQuantity(BigDecimal.valueOf(9)));
    }

    @Test
    void should_update_quantity_when_updating_quantity_larger_than_current_one() {
        Order order = new Order("1", BigDecimal.valueOf(10), BigDecimal.valueOf(10));

        order.updateQuantity(BigDecimal.valueOf(11));

        assertEquals(BigDecimal.valueOf(11), order.getTotalQuantity());
    }
}
