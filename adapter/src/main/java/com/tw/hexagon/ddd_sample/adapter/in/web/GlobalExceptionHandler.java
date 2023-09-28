package com.tw.hexagon.ddd_sample.adapter.in.web;

import com.tw.hexagon.ddd_sample.domain.order.exception.CurrencyNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CurrencyNotFoundException.class)
    public ErrorResponse handleCurrencyNotFoundException(CurrencyNotFoundException e) {
        log.error(e.getMessage(), e);

        return new ErrorResponse(e.getCode(), e.getMessage());
    }

}
