package com.tw.hexagon.ddd_sample.adapter.in.web.order;

import com.tw.hexagon.ddd_sample.adapter.in.web.GlobalExceptionHandler;
import com.tw.hexagon.ddd_sample.application.order.port.in.command.OrderTotalPriceCalculateCommand;
import com.tw.hexagon.ddd_sample.application.order.port.in.usecase.OrderUseCase;
import com.tw.hexagon.ddd_sample.domain.order.exception.CurrencyNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {OrderController.class, GlobalExceptionHandler.class})
@ContextConfiguration(classes = {OrderController.class, GlobalExceptionHandler.class})
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderUseCase orderUseCase;

    @Test
    void should_return_200_and_calculate_total_price_of_order_when_currency_is_usd() throws Exception {
        mockMvc.perform(get("/orders/1/total-price?currency=USD")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        then(orderUseCase).should()
                .calculateTotalPriceWithCurrency(eq(new OrderTotalPriceCalculateCommand("1", "USD")));
    }

    @Test
    void should_return_400_and_error_response_when_invalid_currency_is_sent() throws Exception {
        String invalidCurrency = "INVALID";
        OrderTotalPriceCalculateCommand command = new OrderTotalPriceCalculateCommand("1", invalidCurrency);
        when(orderUseCase.calculateTotalPriceWithCurrency(command)).thenThrow(new CurrencyNotFoundException(invalidCurrency));

        mockMvc.perform(get("/orders/1/total-price?currency=INVALID")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("1000"))
                .andExpect(jsonPath("$.message").value("Currency INVALID not found"));

        then(orderUseCase).should().calculateTotalPriceWithCurrency(command);
    }
}
