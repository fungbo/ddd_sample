package com.tw.hexagon.ddd_sample.domain.order.model;

import com.tw.hexagon.ddd_sample.domain.order.exception.OrderAmountException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private String id;

    private BigDecimal totalQuantity;

    private BigDecimal unitPrice;

    public BigDecimal getTotalPrice() {
        return totalQuantity.multiply(unitPrice);
    }

    public void updateQuantity(BigDecimal totalQuantity) {
        if (totalQuantity.compareTo(this.totalQuantity) < 0) {
            throw new OrderAmountException("Total quantity must be greater than or equal to previous total quantity");
        }

        this.totalQuantity = totalQuantity;
    }
}
