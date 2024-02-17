package com.piggybank.piggybank.response;

import com.piggybank.piggybank.entity.TransactionEntity;
import lombok.Data;

@Data
public class CreateTransactionResponse {
    private TransactionEntity transaction;
}
