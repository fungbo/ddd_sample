package com.tw.hexagon.ddd_sample.adapter.out.persistence.order;

import com.tw.hexagon.ddd_sample.adapter.out.persistence.order.entity.OrderEntity;
import com.tw.hexagon.ddd_sample.application.order.exception.OrderNotFoundException;
import com.tw.hexagon.ddd_sample.application.order.port.out.OrderRepository;
import com.tw.hexagon.ddd_sample.domain.order.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderPersistenceAdapter implements OrderRepository {

    private final OrderJPARepository orderJPARepository;

    @Override
    public Optional<Order> findById(String id) {
        OrderEntity orderEntity = orderJPARepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("OrderId " + id + " not found"));
        Order order = OrderMapper.INSTANCE.toDomain(orderEntity);

        return Optional.of(order);
    }

    @Override
    public String save(Order order) {
        OrderEntity orderEntity = OrderMapper.INSTANCE.toEntity(order);
        log.info("save order: {}", orderEntity);
        OrderEntity saved = orderJPARepository.save(orderEntity);

        return saved.getId();
    }
}
