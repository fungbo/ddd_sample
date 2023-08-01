package com.tw.hexagon.ddd_sample.adapter.in.web.order.request;

import java.math.BigDecimal;

public record OrderCreateRequest(BigDecimal totalQuantity, BigDecimal unitPrice) {
}
