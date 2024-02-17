package com.piggybank.piggybank.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateTransactionRequest {
    private String username;
    private boolean isBuying;
    private String currency;
    private BigDecimal amount;
}
