package com.piggybank.piggybank.service;

import com.piggybank.piggybank.entity.TransactionEntity;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {

    TransactionEntity createNewTransaction(Long userId,
                                           boolean isBuying,
                                           String currency,
                                           BigDecimal amount);

    int getOperationCountFromLast24Hours(Long userId);

    List<TransactionEntity> findAllByUserId(Long userId);
}
