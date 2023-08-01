package com.tw.hexagon.ddd_sample.adapter.in.web.order;

import com.tw.hexagon.ddd_sample.adapter.in.web.order.request.OrderQuantityChangeRequest;
import com.tw.hexagon.ddd_sample.adapter.in.web.order.response.OrderFindResponse;
import com.tw.hexagon.ddd_sample.application.order.port.in.command.OrderQuantityChangeCommand;
import com.tw.hexagon.ddd_sample.application.order.port.in.result.OrderQueryResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderQuantityChangeCommand toQuantityChangeCommand(String id, OrderQuantityChangeRequest orderQuantityChangeRequest);

    OrderFindResponse toOrderResponse(OrderQueryResult orderModel);

}
