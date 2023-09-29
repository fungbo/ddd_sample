package com.tw.hexagon.ddd_sample.adapter.out.persistence.order;

import com.tw.hexagon.ddd_sample.adapter.out.persistence.order.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderJPARepository extends CrudRepository<OrderEntity, String> {
}
