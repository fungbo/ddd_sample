package com.tw.hexagon.ddd_sample.adapter.in.web.order.response;

import java.math.BigDecimal;

public record OrderTotalPriceCalculateResponse(String id, BigDecimal totalPrice) {
}
