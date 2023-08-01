package com.tw.hexagon.ddd_sample.application.order.port.in.result;

import java.math.BigDecimal;

public record OrderQueryResult(String id, BigDecimal totalQuantity, BigDecimal unitPrice) {
}
