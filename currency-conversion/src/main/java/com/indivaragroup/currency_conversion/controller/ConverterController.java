package com.indivaragroup.currency_conversion.controller;

import com.indivaragroup.currency_conversion.dto.ConversionRequest;
import com.indivaragroup.currency_conversion.dto.ConversionResponse;
import com.indivaragroup.currency_conversion.dto.RateResponse;
import com.indivaragroup.currency_conversion.service.ConverterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ConverterController {
    private final ConverterService converterService;
    private final Environment environment;

    @Value("${app.message: Unknow Application}")
    private String appMessage;

    @Value("${app.name: Unknow Application}")
    private String appName;

    public ConverterController(ConverterService converterService, Environment environment) {
        this.converterService = converterService;
        this.environment = environment;
    }

    @GetMapping("/converter")
    public ConversionResponse convert(@Valid ConversionRequest request) {
        return converterService.reqConversion(request);
    }

    @GetMapping("/rates")
    public List<RateResponse> getRates() {
        return converterService.getAllRates();
    }

    @GetMapping("/info")
    public Map<String, String> getInfo() {
        return Map.of("applicationName", appName);
    }

    @GetMapping("/profile")
    public Map<String, String> getProfile() {
        return Map.of("message", appMessage);
    }
}
