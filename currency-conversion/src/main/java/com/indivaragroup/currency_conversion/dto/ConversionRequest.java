package com.indivaragroup.currency_conversion.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ConversionRequest {

    @Min(value = 0, message = "Amount must be greater than 0")
    private BigDecimal amount;

    @NotBlank(message = "From currency can't be empty")
    private String from;

    @NotBlank(message = "To currency can't be empty")
    private String to;
}
