package com.indivaragroup.currency_conversion.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class RateProperties {
    private Map<String, BigDecimal> rates;
    private String message;

    public BigDecimal getRate(String from, String to) {
        String key = from.toUpperCase() + "_" + to.toUpperCase();
        BigDecimal rate = rates.get(key);

        if (rate == null) {
            throw new IllegalArgumentException("Rate not found for: " + key);
        }

        return rate;
    }
}
