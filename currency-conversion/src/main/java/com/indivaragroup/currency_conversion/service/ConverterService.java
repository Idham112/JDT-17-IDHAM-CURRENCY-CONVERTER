package com.indivaragroup.currency_conversion.service;

import com.indivaragroup.currency_conversion.config.RateProperties;
import com.indivaragroup.currency_conversion.converter.CurrencyConverter;
import com.indivaragroup.currency_conversion.dto.ConversionRequest;
import com.indivaragroup.currency_conversion.dto.ConversionResponse;
import com.indivaragroup.currency_conversion.dto.RateResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConverterService {
    private final CurrencyConverter currencyConverter;
    private final RateProperties rateProperties;

    public ConverterService(CurrencyConverter currencyConverter, RateProperties rateProperties) {
        this.currencyConverter = currencyConverter;
        this.rateProperties = rateProperties;
    }

    public ConversionResponse reqConversion(ConversionRequest request) {
        BigDecimal result = currencyConverter.convert(
                request.getAmount(),
                request.getFrom(),
                request.getTo()
        );
        return ConversionResponse.builder()
                .amount(request.getAmount())
                .from(request.getFrom())
                .to(request.getTo())
                .rate(rateProperties.getRate(request.getFrom(), request.getTo()))
                .result(result)
                .build();
    }

    public List<RateResponse> getAllRates() {
        return rateProperties.getRates().entrySet().stream()
                .map(entry -> {
                    // Split the key (e.g., "USD_IDR") back into "USD" and "IDR"
                    String[] currencies = entry.getKey().split("_");
                    return RateResponse.builder()
                            .from(currencies[0])
                            .to(currencies[1])
                            .rate(entry.getValue())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
