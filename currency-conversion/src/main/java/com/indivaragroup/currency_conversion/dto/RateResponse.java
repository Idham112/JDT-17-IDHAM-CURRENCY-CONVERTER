package com.indivaragroup.currency_conversion.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class RateResponse {
    private String from;
    private String to;
    private BigDecimal rate;
}
