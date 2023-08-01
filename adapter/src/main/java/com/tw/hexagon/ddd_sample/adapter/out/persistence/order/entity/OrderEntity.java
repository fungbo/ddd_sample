package com.tw.hexagon.ddd_sample.adapter.out.persistence.order.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class OrderEntity {

    private String id;

    private BigDecimal totalQuantity;

    private BigDecimal unitPrice;
}
