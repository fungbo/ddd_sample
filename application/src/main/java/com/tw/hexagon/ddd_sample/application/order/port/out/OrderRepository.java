package com.tw.hexagon.ddd_sample.application.order.port.out;


import com.tw.hexagon.ddd_sample.domain.order.model.Order;

import java.util.Optional;

public interface OrderRepository {

    Optional<Order> findById(String id);

    String save(Order order);
}
