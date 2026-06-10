package com.indivaragroup.currency_conversion.converter;

import com.indivaragroup.currency_conversion.config.RateProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class CurrencyConverter {
    private final RateProperties  rateProperties;

    public CurrencyConverter(RateProperties rateProperties) {
        this.rateProperties = rateProperties;
    }

    public BigDecimal convert(BigDecimal amount, String from, String to) {
        BigDecimal rate = rateProperties.getRate(from, to);

        return amount.multiply(rate).setScale(2, RoundingMode.HALF_UP);
    }


}
