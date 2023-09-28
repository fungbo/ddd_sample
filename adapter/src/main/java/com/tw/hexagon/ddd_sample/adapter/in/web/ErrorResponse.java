package com.tw.hexagon.ddd_sample.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private String code;

    private String message;
}
