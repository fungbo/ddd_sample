package com.tw.hexagon.ddd_sample.adapter.in.web.order;

import com.tw.hexagon.ddd_sample.application.order.port.in.command.OrderTotalPriceCalculateCommand;
import com.tw.hexagon.ddd_sample.application.order.port.in.usecase.OrderUseCasePort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = OrderController.class)
@ContextConfiguration(classes = {OrderController.class})
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderUseCasePort orderUseCasePort;

    @Test
    void should_calculate_total_price_of_order_when_currency_is_usd() throws Exception {
        mockMvc.perform(get("/orders/1/total-price?currency=USD")
                        .header("Content-Type", "application/json"))
                .andExpect(status().isOk());

        then(orderUseCasePort).should()
                .calculateTotalPriceWithCurrency(eq(new OrderTotalPriceCalculateCommand("1", "USD")));
    }
}
