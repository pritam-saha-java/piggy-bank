package com.piggybank.piggybank.service;

import com.piggybank.piggybank.entity.TransactionEntity;
import com.piggybank.piggybank.request.CreateTransactionRequest;
import com.piggybank.piggybank.request.FindAllTransactionsByUserRequest;
import com.piggybank.piggybank.response.CreateTransactionResponse;
import com.piggybank.piggybank.response.FindAllTransactionsByUserResponse;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {

    TransactionEntity createNewTransaction(Long userId,
                                           boolean isBuying,
                                           String currency,
                                           BigDecimal amount);

    int getOperationCountFromLast24Hours(Long userId);

    List<TransactionEntity> findAllByUserId(Long userId);

    CreateTransactionResponse createTransaction(CreateTransactionRequest request);

    FindAllTransactionsByUserResponse findAll(FindAllTransactionsByUserRequest request);
}
