package com.tw.hexagon.ddd_sample.adapter.out.persistence.order;

import com.tw.hexagon.ddd_sample.adapter.out.persistence.order.entity.OrderEntity;
import com.tw.hexagon.ddd_sample.application.order.port.out.OrderPersistencePort;
import com.tw.hexagon.ddd_sample.domain.order.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class OrderPersistenceAdapter implements OrderPersistencePort {

    @Override
    public Optional<Order> findById(String id) {
        OrderEntity orderEntity = new OrderEntity(id, BigDecimal.valueOf(100), BigDecimal.valueOf(100.5));
        Order order = OrderMapper.INSTANCE.toDomain(orderEntity);

        return Optional.of(order);
    }

    @Override
    public String save(Order order) {
        OrderEntity orderEntity = OrderMapper.INSTANCE.toEntity(order);
        log.info("save order: {}", orderEntity);

        return UUID.randomUUID().toString();
    }
}
