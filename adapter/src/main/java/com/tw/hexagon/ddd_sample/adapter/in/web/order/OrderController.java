package com.tw.hexagon.ddd_sample.adapter.in.web.order;

import com.tw.hexagon.ddd_sample.adapter.in.web.order.request.OrderCreateRequest;
import com.tw.hexagon.ddd_sample.adapter.in.web.order.request.OrderQuantityChangeRequest;
import com.tw.hexagon.ddd_sample.adapter.in.web.order.response.OrderCreateResponse;
import com.tw.hexagon.ddd_sample.adapter.in.web.order.response.OrderFindResponse;
import com.tw.hexagon.ddd_sample.adapter.in.web.order.response.OrderTotalPriceCalculateResponse;
import com.tw.hexagon.ddd_sample.application.order.port.in.command.OrderCreateCommand;
import com.tw.hexagon.ddd_sample.application.order.port.in.command.OrderQuantityChangeCommand;
import com.tw.hexagon.ddd_sample.application.order.port.in.command.OrderTotalPriceCalculateCommand;
import com.tw.hexagon.ddd_sample.application.order.port.in.result.OrderCreateResult;
import com.tw.hexagon.ddd_sample.application.order.port.in.result.OrderQueryResult;
import com.tw.hexagon.ddd_sample.application.order.port.in.usecase.OrderUseCasePort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderUseCasePort orderUseCasePort;

    @PostMapping
    public OrderCreateResponse createOrder(@RequestBody OrderCreateRequest request) {
        OrderCreateCommand command = new OrderCreateCommand(request.totalQuantity(), request.unitPrice());
        OrderCreateResult orderCreateResult = orderUseCasePort.createOrder(command);

        return new OrderCreateResponse(orderCreateResult.id());
    }

    @PutMapping("/{id}")
    public void updateOrderQuantity(@PathVariable String id, @RequestBody OrderQuantityChangeRequest orderQuantityChangeRequest) {
        OrderQuantityChangeCommand command = OrderMapper.INSTANCE.toQuantityChangeCommand(id, orderQuantityChangeRequest);

        orderUseCasePort.updateOrderQuantity(command);
    }

    @GetMapping("/{id}/total-price")
    public OrderTotalPriceCalculateResponse calculateTotalPriceWithCurrency(@PathVariable String id, @RequestParam String currency) {
        OrderTotalPriceCalculateCommand command = new OrderTotalPriceCalculateCommand(id, currency);
        BigDecimal totalPrice = orderUseCasePort.calculateTotalPriceWithCurrency(command);

        return new OrderTotalPriceCalculateResponse(id, totalPrice);
    }

    @GetMapping("/{id}")
    public OrderFindResponse findOrderById(@PathVariable String id) {
        OrderQueryResult orderModel = orderUseCasePort.findById(id);

        return OrderMapper.INSTANCE.toOrderResponse(orderModel);
    }
}
