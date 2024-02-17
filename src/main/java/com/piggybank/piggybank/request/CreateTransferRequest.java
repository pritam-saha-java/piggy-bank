package com.piggybank.piggybank.request;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class CreateTransferRequest {

    private String senderUsername;
    private String receiverTcno;
    private String currency;
    private BigDecimal amount;
}
