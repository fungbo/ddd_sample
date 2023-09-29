package com.tw.hexagon.ddd_sample.adapter.out.persistence.order;

import com.tw.hexagon.ddd_sample.adapter.out.persistence.order.entity.OrderEntity;
import com.tw.hexagon.ddd_sample.domain.order.model.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderPersistenceAdapterTest {

    @InjectMocks
    private OrderPersistenceAdapter orderPersistenceAdapter;

    @Mock
    private OrderJPARepository orderJPARepository;

    @Captor
    private ArgumentCaptor<OrderEntity> orderEntityCaptor;

    @Test
    void should_find_order_when_find_method_is_called() {
        when(orderJPARepository.findById(anyString()))
                .thenReturn(Optional.of(new OrderEntity("1", BigDecimal.valueOf(100), BigDecimal.valueOf(100.5))));

        Optional<Order> orderOpt = orderPersistenceAdapter.findById("1");
        assertTrue(orderOpt.isPresent());
        assertEquals(0, BigDecimal.valueOf(100).compareTo(orderOpt.get().getTotalQuantity()));
        assertEquals(0, BigDecimal.valueOf(100.5).compareTo(orderOpt.get().getUnitPrice()));
    }

    @Test
    void should_save_order_when_save_method_is_called() {
        when(orderJPARepository.save(any(OrderEntity.class)))
                .thenAnswer(invocation -> {
                    OrderEntity savingEntity = invocation.getArgument(0, OrderEntity.class);
                    return new OrderEntity(UUID.randomUUID().toString(), savingEntity.getTotalQuantity(), savingEntity.getUnitPrice());
                });

        Order order = new Order();
        order.setUnitPrice(BigDecimal.valueOf(100));
        order.setTotalQuantity(BigDecimal.valueOf(10));
        orderPersistenceAdapter.save(order);

        verify(orderJPARepository).save(orderEntityCaptor.capture());
        OrderEntity orderEntity = orderEntityCaptor.getValue();

        assertEquals(0, order.getUnitPrice().compareTo(orderEntity.getUnitPrice()));
        assertEquals(0, order.getTotalQuantity().compareTo(orderEntity.getTotalQuantity()));
    }
}
