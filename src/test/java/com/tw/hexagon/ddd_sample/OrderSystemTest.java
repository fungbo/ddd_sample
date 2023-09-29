package com.tw.hexagon.ddd_sample;

import com.tw.hexagon.ddd_sample.adapter.in.web.order.response.OrderTotalPriceCalculateResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.Objects;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderSystemTest {

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    @Sql(scripts = "/fixtures/insert_order.sql")
    void should_calculate_total_price_of_order_when_currency_is_usd() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<Void> request = new HttpEntity<>(null, headers);

        ResponseEntity<OrderTotalPriceCalculateResponse> response = restTemplate.exchange(
                "/orders/{id}/total-price?currency={currency}",
                HttpMethod.GET,
                request,
                OrderTotalPriceCalculateResponse.class,
                "1",
                "USD");

        then(response.getStatusCode().value()).isEqualTo(200);
        then(BigDecimal.valueOf(7000).compareTo(Objects.requireNonNull(response.getBody()).totalPrice())).isEqualTo(0);
    }
}
