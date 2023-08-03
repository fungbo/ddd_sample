package com.tw.hexagon.ddd_sample.adapter.out.persistence.currency;

import com.tw.hexagon.ddd_sample.adapter.out.persistence.currency.dto.CurrencyQueryDTO;
import com.tw.hexagon.ddd_sample.application.currency.port.out.CurrencyAmountGateway;
import com.tw.hexagon.ddd_sample.domain.currency.model.CurrencyAmount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
public class CurrencyAmountPersistenceAdapter implements CurrencyAmountGateway {

    private static final Map<String, Double> CURRENCY_DICT = Map.of("CNY", 1D, "USD", 7D, "EUR", 8D);

    @Override
    public CurrencyAmount getExchangeRate(String currency) {
        CurrencyQueryDTO currencyDTO = new CurrencyQueryDTO(currency);
        log.info("call the erp to get the exchange rate of {}", currencyDTO);

        Double rate = Optional.ofNullable(CURRENCY_DICT.get(currency.toUpperCase(Locale.ROOT)))
                .orElseThrow(() -> new IllegalArgumentException("Unsupported currency " + currency));

        return new CurrencyAmount(currency, rate);
    }
}
