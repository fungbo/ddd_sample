package com.tw.hexagon.ddd_sample.adapter.out.persistence.order;

import com.tw.hexagon.ddd_sample.adapter.out.persistence.order.entity.OrderEntity;
import com.tw.hexagon.ddd_sample.domain.order.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderEntity toEntity(Order order);

    Order toDomain(OrderEntity orderEntity);
}
